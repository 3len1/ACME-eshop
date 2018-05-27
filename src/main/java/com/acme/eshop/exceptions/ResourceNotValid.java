package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class ResourceNotValid extends RuntimeException {
    public ResourceNotValid(String message) {
        super(message);
    }
}
