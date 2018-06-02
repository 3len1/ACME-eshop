package com.acme.eshop.service;

import com.acme.eshop.converter.OrderConverter;
import com.acme.eshop.domain.CartItem;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.OrderItem;
import com.acme.eshop.domain.User;
import com.acme.eshop.dto.UserCountDto;
import com.acme.eshop.exceptions.*;
import com.acme.eshop.resources.ItemResource;
import com.acme.eshop.resources.OrderResource;
import com.acme.eshop.repository.*;
import com.acme.eshop.utils.DateUtils;
import com.acme.eshop.utils.PriceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component("orderService")
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository itemRepository;
    @Autowired
    CartService cartService;
    @Autowired
    OrderConverter orderConverter;

    @Override
    public Page<Order> getAllOrder(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user != null && user.isAdmin())
            return orderRepository.findAll(pageable);
        else if (user != null)
            return orderRepository.findByUser(user, pageable);
        else {
            log.warn("User [{}] does nto exist", userId);
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Page<Order> getAllByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user == null) {
            log.warn("User [{}] does nto exist", userId);
            throw new UserNotFoundException("User not found");
        }
        return orderRepository.findByUser(user, pageable);
    }

    @Override
    public Order showOrder(@NotNull String orderCode, @NotNull Long userId) {
        Order order = orderRepository.findOneByOrderCode(orderCode);
        if (order == null) {
            log.warn("Order [{}] does not exist", orderCode);
            throw new OrderNotFoundException("Order not found");
        }
        User user = userRepository.findById(userId).orElseGet(null);
        if (user == null) {
            log.warn("User [{}] does nto exist", userId);
            throw new UserNotFoundException("User not found");
        } else if (order.getUser().getId().equals(userId) || user.isAdmin()) {
            return order;
        }
        throw new WrongCredentialsException("You have not authority to see this order");
    }

    @Transactional
    @Override
    public Order payOrder(String orderCode, Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user == null) {
            log.warn("User [{}] does nto exist", userId);
            throw new UserNotFoundException("User not found");
        }
        Order order = showOrder(orderCode, userId);
        if (order != null) {
            order.setPaymentDate(DateUtils.epochNow());
            orderRepository.save(order);
            log.info("User [{}] pay order [{}]", userId, orderCode);
            return order;
        }
        log.warn("Order [{}] can't not found", orderCode);
        throw new WrongCredentialsException("You have not authority to see this order");
    }

    @Transactional
    @Override
    public synchronized Order createOrder(OrderResource orderResource, Long userId) {
        if (orderRepository.findOneByOrderCode(orderResource.getOrderCode()) != null) {
            log.warn("Order with code [{}] already exist", orderResource.getOrderCode());
            throw new OrderAlreadyExistException("This orderCode is given to another order");
        }
        User loginUser = userRepository.findById(userId).get();
        if (loginUser == null) {
            log.warn("User with id [{}] not found", userId);
            throw new UserNotFoundException("User not found");
        }
        Optional.ofNullable(cartService.getCartByUser(userId)).ifPresent(cart -> {
            Set<CartItem> cartsItems = cart.getItems();
            Order order = orderConverter.getOrder(orderResource);
            order.setUser(loginUser);
            order.setCreatedDate(DateUtils.epochNow());
            orderRepository.save(order);
            cartsItems.forEach(cartItem -> {
                cartItem.setCart(null);
                cartItem.getProduct().increasePurchased(cartItem.getAmount());
                cartItem.getProduct().minusStockAmount(cartItem.getAmount());
                productRepository.save(cartItem.getProduct());
                OrderItem orderItem = new OrderItem();
                orderItem.setAmount(cartItem.getAmount());
                orderItem.setPrice(PriceUtils.bigDecimalMultiply(cartItem.getProduct().getPrice(), cartItem.getAmount()));
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setCreatedDate(DateUtils.epochNow());
                orderItem.setOrder(order);
                itemRepository.save(orderItem);
                order.calculatePrice(orderItem.getPrice());
            });

            log.info("User [{}] create order [{}] successfully", userId, orderResource.getOrderCode());
        });
        return orderRepository.findOneByOrderCode(orderResource.getOrderCode());
    }

    @Transactional
    @Override
    public synchronized Order addItemsToOrder(String orderCode, ItemResource addedItem, Long userId) {
        Order order = showOrder(orderCode, userId);
        if (order != null && (order.getPaymentDate() != null || order.isCanceled())) {
            log.warn("User [{}] try to add items to order [{}] which is payed or canceled", userId,
                    order.getOrderCode());
            throw new OrderAlreadyPayed("Order is already paid or canceled");
        }
        OrderItem item = new OrderItem();
        Optional.ofNullable(productRepository.findByProductCode(addedItem.getProductCode())).ifPresent(product -> {
            item.setProduct(product);
            item.setAmount((addedItem.getAmount() != null) ? Integer.parseInt(addedItem.getAmount()) : 1);
            item.setPrice(PriceUtils.bigDecimalMultiply(product.getPrice(), item.getAmount()));
            item.setCreatedDate(DateUtils.epochNow());
            item.setOrder(order);
            product.minusStockAmount(item.getAmount());
            product.increasePurchased(item.getAmount());
            productRepository.save(product);
            order.calculatePrice(item.getPrice());
        });
        if (item.getOrder() != null) {
            itemRepository.save(item);
            log.info("Product [{}] added to order [{}]", addedItem.getProductCode(), orderCode);
            return orderRepository.save(item.getOrder());
        }
        log.warn("Product [{}] can not add to order [{}]", addedItem.getProductCode(), orderCode);
        throw new ProductNotFoundException("Can't found a product");
    }

    @Transactional
    @Override
    public synchronized Order removeItemFromOrder(String orderCode, String productCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        Optional.ofNullable(productRepository.findByProductCode(productCode)).ifPresent(product -> {
            if (order != null) {
                product.increaseStockAmount(itemRepository.findOneByProductAndOrder(product, order).getAmount());
                product.minusPurchased(itemRepository.findOneByProductAndOrder(product, order).getAmount());
                itemRepository.deleteAllByProductAndOrder(product, order);
                log.info("Remove product [{}] from order [{}]", productCode, orderCode);
            }
        });
        return order;
    }

    @Transactional
    @Override
    public boolean cancelOrder(String orderCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        if (order != null && order.getPaymentDate() != null) {
            order.setCanceled(true);
            orderRepository.save(order);
            log.info("User [{}] cancel order [{}]", userId, orderCode);
            return true;
        }
        log.warn("User [{}] can't cancel order [{}]", userId, orderCode);
        return false;
    }

    @Transactional
    @Override
    public void deleteOrder(String orderCode, Long userId) {
        Optional.ofNullable(showOrder(orderCode, userId)).ifPresent(order -> {
            itemRepository.deleteAllByOrder(order);
            orderRepository.delete(order);
            log.info("Admin delete user's [{}] order [{}]", userId, orderCode);
        });
    }

    @Override
    public List<OrderItem> getAllItemsFromOrder(String orderCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        return new ArrayList<>(order.getItems());
    }

    @Override
    public List<UserCountDto> getUserOrders(boolean isAdmin) {
        return orderRepository.getUserOrders();
    }
}
