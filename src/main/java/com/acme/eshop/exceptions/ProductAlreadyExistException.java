package com.acme.eshop.exceptions;

/**
 * Created by Eleni on 5/26/2018.
 */
public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
