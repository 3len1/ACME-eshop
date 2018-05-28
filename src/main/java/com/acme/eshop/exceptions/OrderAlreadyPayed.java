package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/29/2018.
 */
public class OrderAlreadyPayed extends RuntimeException {
    public OrderAlreadyPayed(String message) {
        super(message);
    }
}
