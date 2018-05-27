package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
