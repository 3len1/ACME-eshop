package com.acme.eshop.controller;

import com.acme.eshop.exceptions.*;
import com.acme.eshop.resources.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Eleni on 5/27/2018.
 */
@ControllerAdvice()
public class AdviceController {

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleErrors(WrongCredentialsException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessage("Access not aloud", 401));
    }

    @ExceptionHandler(NotIdenticalUserException.class)
    public ResponseEntity<ErrorMessage> handleErrors(NotIdenticalUserException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessage("Not a valid login season", 401));
    }

    @ExceptionHandler(NotIdenticalAddressForUser.class)
    public ResponseEntity<ErrorMessage> handleErrors(NotIdenticalAddressForUser ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("User can have only one address", 400));
    }

    @ExceptionHandler(ResourceNotValid.class)
    public ResponseEntity<ErrorMessage> handleErrors(ResourceNotValid ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("The give resource is not valid please try again", 400));
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleErrors(CategoryAlreadyExistException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage("Product category already exist", 403));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleErrors(CategoryNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Product category not found", 404));
    }

    @ExceptionHandler(OrderAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleErrors(OrderAlreadyExistException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage("Order already exist", 403));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleErrors(OrderNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Order not found", 404));
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleErrors(ProductAlreadyExistException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage("Product already exist", 403));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleErrors(ProductNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Product not found", 404));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleErrors(UserAlreadyExistException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage("User already exist", 403));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleErrors(UserNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("User not found", 404));
    }


}

