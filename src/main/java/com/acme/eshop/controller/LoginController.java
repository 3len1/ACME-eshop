package com.acme.eshop.controller;

import com.acme.eshop.domain.User;
import com.acme.eshop.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping(value = "/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginParams) {
        String email = loginParams.get("email");
        String password = loginParams.get("password");

        if (email == null || password == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        User user = loginService.logIn(email, password);
        return (user!=null)?ResponseEntity.status(HttpStatus.OK).body(user):new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Log out")
    @PostMapping(value = "/logout")
    public void logout(@RequestHeader("sessionID") UUID sessionID) {
        loginService.logOut(sessionID);
    }
}
