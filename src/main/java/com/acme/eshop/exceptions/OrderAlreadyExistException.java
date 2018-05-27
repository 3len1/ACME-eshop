package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(String message) {
        super(message);
    }
}
