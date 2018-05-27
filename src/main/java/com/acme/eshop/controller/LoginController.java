package com.acme.eshop.controller;

import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.NotIdenticalUserException;
import com.acme.eshop.exceptions.WrongCredentialsException;
import com.acme.eshop.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Eleni on 5/24/2018.
 */
@RestController
@Api(description = "Log in/ Log out", tags = "LogIn")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "Log in")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody Map<String, String> loginParams) {
        String email = loginParams.get("email");
        String password = loginParams.get("password");
        User user = loginService.logIn(email, password);
        return (user != null) ? ResponseEntity.status(HttpStatus.OK).body(user) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Log out")
    @GetMapping(value = "/logout")
    public void logout(@RequestHeader("sessionID") UUID sessionID) {
        loginService.logOut(sessionID);
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<String> handleCredentialsError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Given credentials are not correct");

    }

    @ExceptionHandler(NotIdenticalUserException.class)
    public ResponseEntity<String> handleIdenticalError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Connection error");

    }
}
