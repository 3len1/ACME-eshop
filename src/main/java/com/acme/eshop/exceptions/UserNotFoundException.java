package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
