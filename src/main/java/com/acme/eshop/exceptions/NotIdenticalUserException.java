package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class NotIdenticalUserException extends RuntimeException {
    public NotIdenticalUserException(String message) {
        super(message);
    }
}
