package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class NotIdenticalAddressForUser extends RuntimeException {
    public NotIdenticalAddressForUser(String message) {
        super(message);
    }
}
