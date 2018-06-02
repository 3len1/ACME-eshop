package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException(String message) {
        super(message);
    }
}
