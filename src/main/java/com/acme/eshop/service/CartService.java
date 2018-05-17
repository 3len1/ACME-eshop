package com.acme.eshop.service;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.dto.ItemDto;
import org.springframework.stereotype.Service;

/**
 * Created by Eleni on 17/5/2018.
 */
@Service
public interface CartService {

    Cart getCartByUser(Long userId);
    Cart addItemToCart(ItemDto addedItem, Long userId);
    Cart deleteItemFromCart(String productCode, Long userId);
    void emptyCart(Long userId);
}
