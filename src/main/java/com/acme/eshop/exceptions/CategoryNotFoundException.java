package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
