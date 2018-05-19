package com.acme.eshop.service;

import com.acme.eshop.converter.OrderConverter;
import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.User;
import com.acme.eshop.dto.ItemDto;
import com.acme.eshop.dto.OrderDto;
import com.acme.eshop.repository.*;
import com.acme.eshop.utils.DateUtils;
import com.acme.eshop.utils.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartService cartService;
    @Autowired
    OrderConverter orderConverter;

    @Override
    public Page<Order> getAllOrder(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).get();
        if (user != null && user.isAdmin())
            return orderRepository.findAll(pageable);
        else if (user != null)
            return orderRepository.findByUser(user, pageable);
        else
            return null;
    }

    @Override
    public Page<Order> getAllByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).get();
        return (user != null) ? orderRepository.findByUser(user, pageable) : null;

    }

    @Override
    public Order showOrder(String orderCode, Long userId) {
        Order order = orderRepository.findOneByOrderCode(orderCode);
        return (order != null && (order.getUser().getId().equals(userId))||order.getUser().isAdmin()) ? order : null;
    }

    @Override
    public Order payOrder(String orderCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        if (order != null) {
            order.setPaymentDate(DateUtils.epochNow());
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public Order createOrder(OrderDto orderDto, Long userId) {
        if (orderRepository.findOneByOrderCode(orderDto.getOrderCode()) != null)
            return null;
        Optional.ofNullable(cartService.getCartByUser(userId)).ifPresent(cart -> {
                List<Item> cartsItems = cart.getItems();
                Order order = orderConverter.getOrder(orderDto);
                order.setItems(cartsItems.stream().map(item -> {
                    item.setOrder(order);
                    item.setCart(null);
                    return itemRepository.save(item);
                }).collect(Collectors.toList()));
                cart.setItems(null);
            cartRepository.save(cart);
            orderRepository.save(order);
        });
        Order o = orderRepository.findOneByOrderCode(orderDto.getOrderCode());
        return (o != null) ? o : null;
    }

    @Override
    public Order addItemsToOrder(String orderCode, ItemDto addedItem, Long userId) {
        Item item = new Item();
        Optional.ofNullable(productRepository.findByProductCode(addedItem.getProductCode())).ifPresent(product -> {
            item.setProduct(product);
            item.setAmount((addedItem.getAmount() != null) ? Integer.parseInt(addedItem.getAmount()) : 1);
            item.setPrice(PriceUtils.bigDecimalMultiply(product.getPrice(), item.getAmount()));
            item.setCreatedDate(DateUtils.epochNow());
            item.setOrder(showOrder(orderCode, userId));
        });

        if (item.getOrder() != null) {
            itemRepository.save(item);
            return orderRepository.save(item.getOrder());
        }
        return null;
    }

    @Override
    public Order removeItemFromOrder(String orderCode, String productCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        Optional.ofNullable(productRepository.findByProductCode(productCode)).ifPresent(product -> {
            if (order != null)
                itemRepository.deleteAllByProductAndOrder(product, order);
        });
        return (order != null) ? order : null;
    }

    @Override
    public boolean cancelOrder(String orderCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        if (order != null) {
            order.setCanceled(true);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public void deleteOrder(String orderCode, Long userId) {
        Optional.ofNullable(showOrder(orderCode, userId)).ifPresent(order -> {
            itemRepository.deleteAllByOrder(order);
            orderRepository.delete(order);
        });
    }

    @Override
    public List<Item> getAllItemsFromOrder(String orderCode, Long userId) {
        Order order = showOrder(orderCode, userId);
        return (order != null) ? itemRepository.findByOrder(order) : null;
    }
}
