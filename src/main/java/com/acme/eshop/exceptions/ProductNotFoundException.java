package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
