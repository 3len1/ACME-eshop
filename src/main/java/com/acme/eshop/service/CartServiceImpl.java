package com.acme.eshop.service;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.ProductNotFoundException;
import com.acme.eshop.exceptions.UserNotFoundException;
import com.acme.eshop.resources.ItemResource;
import com.acme.eshop.repository.CartRepository;
import com.acme.eshop.repository.ItemRepository;
import com.acme.eshop.repository.ProductRepository;
import com.acme.eshop.repository.UserRepository;
import com.acme.eshop.utils.DateUtils;
import com.acme.eshop.utils.PriceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eleni on 17/5/2018.
 */
@Component("cartService")
public class CartServiceImpl implements CartService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Cart getCartByUser(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user == null) {
            log.warn("User [{}] not exist", userId);
            throw new UserNotFoundException("User not found");
        }
        return cartRepository.findOneByUser(user);
    }

    @Transactional
    @Override
    public Cart addItemToCart(ItemResource addedItem, Long userId) {
        Item item = new Item();
        Optional.ofNullable(productRepository.findByProductCode(addedItem.getProductCode())).ifPresent(product -> {
            item.setProduct(product);
            item.setAmount((addedItem.getAmount() != null) ? Integer.parseInt(addedItem.getAmount()) : 1);
            item.setPrice(PriceUtils.bigDecimalMultiply(product.getPrice(), item.getAmount()));
            item.setCreatedDate(DateUtils.epochNow());
            item.setCart(getCartByUser(userId));
            log.info("Product [{}] added [{}] times to user's [{}] cart", product.getProductCode(), item.getAmount(), userId);
            itemRepository.save(item);
        });
        if (item.getCart() != null)
            return cartRepository.save(item.getCart());

        log.warn("Product with code [{}] does not exist", addedItem.getProductCode());
        throw new ProductNotFoundException("Product not found");
    }

    @Transactional
    @Override
    public Cart removeItemFromCart(String productCode, Long userId) {
        Cart cart = getCartByUser(userId);
        Optional.ofNullable(productRepository.findByProductCode(productCode)).ifPresent(product -> {
            if (cart != null) {
                itemRepository.deleteAllByProductAndCart(product, cart);
                log.info("Product [{}] successfully removed from users [{}] cart", productCode, userId);
            }
        });
        if (cart != null) return cart;
        log.warn("Product with code [{}] does not exist", productCode);
        throw new ProductNotFoundException("Product not found");
    }

    @Transactional
    @Override
    public void emptyCart(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user != null)
            Optional.ofNullable(cartRepository.findOneByUser(user)).ifPresent(cart -> {
                itemRepository.deleteAllByCart(cart);
                log.info("Empty user's [{}] cart", userId);
            });
        else {
            log.warn("User [{}] does not exist", userId);
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<Item> getAllItemsFromCart(Long userId) {
        Cart cart = getCartByUser(userId);
        return (cart != null) ? itemRepository.findByCart(cart) : null;
    }
}
