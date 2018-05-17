package com.acme.eshop.service;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.Item;
import com.acme.eshop.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eleni on 17/5/2018.
 */
@Service
public interface CartService {

    Cart getCartByUser(Long userId);

    Cart addItemToCart(ItemDto addedItem, Long userId);

    Cart removeItemFromCart(String productCode, Long userId);

    void emptyCart(Long userId);

    List<Item> getAllItemsFromCart(Long userId);

}