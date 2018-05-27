package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
