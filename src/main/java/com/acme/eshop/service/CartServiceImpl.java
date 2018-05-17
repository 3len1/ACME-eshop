package com.acme.eshop.service;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.User;
import com.acme.eshop.dto.ItemDto;
import com.acme.eshop.repository.CartRepository;
import com.acme.eshop.repository.ItemRepository;
import com.acme.eshop.repository.ProductRepository;
import com.acme.eshop.repository.UserRepository;
import com.acme.eshop.utils.DateUtils;
import com.acme.eshop.utils.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eleni on 17/5/2018.
 */
@Component("cartService")
@Transactional
public class CartServiceImpl implements CartService {
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
        return (user!=null)?cartRepository.findOneByUser(user):null;
    }

    @Override
    public Cart addItemToCart(ItemDto addedItem, Long userId) {
        Item item = new Item();
        Optional.ofNullable(productRepository.findByProductCode(addedItem.getProductCode())).ifPresent(product ->{
            item.setProduct(product);
            item.setAmount((addedItem.getAmount()!=null)?Integer.parseInt(addedItem.getAmount()):1);
            item.setPrice(PriceUtils.bigDecimalMultiply(product.getPrice(), item.getAmount()));
            item.setCreatedDate(DateUtils.epochNow());
            item.setCart(getCartByUser(userId));
        });

        if(item.getCart()!=null){
            itemRepository.save(item);
            return cartRepository.save(item.getCart());
        }
        return null;
    }

    @Override
    public Cart removeItemFromCart(String productCode, Long userId) {
        Cart cart = getCartByUser(userId);
        Optional.ofNullable(productRepository.findByProductCode(productCode)).ifPresent(product ->{
            if (cart!=null)
                itemRepository.deleteAllByProductAndCart(product, cart);
        });
        return (cart!=null)?cart:null;
    }

    @Override
    public void emptyCart(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user!=null)
            Optional.ofNullable(cartRepository.findOneByUser(user)).ifPresent(cart ->{
                itemRepository.deleteAllByCart(cart);
                cart.setItems(null);
                cartRepository.save(cart);
            });
    }

    @Override
    public List<Item> getAllItemsFromCart(Long userId) {
        Cart cart = getCartByUser(userId);
        return (cart !=null)? itemRepository.findByCart(cart):null;
    }
}
