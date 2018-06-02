package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
