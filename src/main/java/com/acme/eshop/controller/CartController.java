package com.acme.eshop.controller;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.CartItem;
import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.WrongCredentialsException;
import com.acme.eshop.resources.ItemResource;
import com.acme.eshop.service.CartService;
import com.acme.eshop.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 5/28/2018.
 */
@RestController
@Api(description = "Cart  Controller", tags = "User's cart")
public class CartController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoginService loginService;
    @Autowired
    CartService cartService;

    @ApiOperation("Get items from user's cart")
    @GetMapping(value = "/{userId}/cart")
    public ResponseEntity<List<CartItem>> getCartByUser(@PathVariable(name = "userId") Long userId,
                                                        @RequestHeader("sessionID") UUID sessionID) {
        User loginUser = loginService.getUser(sessionID);
        if (loginUser.isAdmin() || userId.equals(loginUser.getId()))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cartService.getAllItemsFromCart(userId));
        log.warn("Login user [{}] has not access to see users [{}] cart", loginUser.getId(), userId);
        throw new WrongCredentialsException("Only admin or owner of profile can get cart's items");
    }

    @ApiOperation("User add product to cart")
    @PutMapping(value = "/{userId}/cart")
    public ResponseEntity<Cart> addItemToCart(@PathVariable(name = "userId") Long userId,
                                              @Valid @RequestBody ItemResource itemResource,
                                              @RequestHeader("sessionID") UUID sessionID) {
        User loginUser = loginService.getUser(sessionID);
        if (!userId.equals(loginUser.getId())) {
            log.warn("Login user [{}] has not access to see users [{}] cart", loginUser.getId(), userId);
            throw new WrongCredentialsException("Only owner can add item to his cart");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cartService.addItemToCart(itemResource, userId));
    }

    @ApiOperation("User remove product form cart")
    @DeleteMapping(value = "/{userId}/cart")
    public ResponseEntity<List<CartItem>> removeItemFromCart(@PathVariable(name = "userId") Long userId,
                                                             @NotNull @RequestBody String productCode,
                                                             @RequestHeader("sessionID") UUID sessionID) {
        User loginUser = loginService.getUser(sessionID);
        if (!userId.equals(loginUser.getId())) {
            log.warn("Login user [{}] has not access to see users [{}] cart", loginUser.getId(), userId);
            throw new WrongCredentialsException("Only owner can remove item from his cart");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cartService.removeItemFromCart(productCode, userId));
    }

    @ApiOperation("User remove all products form cart")
    @DeleteMapping(value = "/{userId}/emptyCart")
    public ResponseEntity emptyCart(@PathVariable(name = "userId") Long userId,
                                    @RequestHeader("sessionID") UUID sessionID) {
        User loginUser = loginService.getUser(sessionID);
        if (!userId.equals(loginUser.getId())) {
            log.warn("Login user [{}] has not access to see users [{}] cart", loginUser.getId(), userId);
            throw new WrongCredentialsException("Only owner can empty his cart");
        }
        cartService.emptyCart(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

