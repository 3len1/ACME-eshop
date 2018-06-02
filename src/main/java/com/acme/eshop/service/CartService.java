package com.acme.eshop.service;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.CartItem;
import com.acme.eshop.resources.ItemResource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Eleni on 17/5/2018.
 */
@Service
public interface CartService {

    Cart getCartByUser(Long userId);
    Cart addItemToCart(ItemResource addedItem, Long userId);
    List<CartItem> removeItemFromCart(String productCode, Long userId);
    List<CartItem> getAllItemsFromCart(Long userId);
    void emptyCart(Long userId);

}
