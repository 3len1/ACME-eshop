package com.acme.eshop.controller;

import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.WrongCredentialsException;
import com.acme.eshop.resources.UserResource;
import com.acme.eshop.service.LoginService;
import com.acme.eshop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Eleni on 5/27/2018.
 */
@RestController
@Api(description = "User controller", tags = "User")
public class UserController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @ApiOperation("Profile")
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<User> showProfile(@PathVariable(name = "userId") Long userId,
                                            @RequestHeader("sessionId") UUID sessionId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.showUser(userId));
    }

    @ApiOperation("Create account")
    @PostMapping(value = "/users")
    public ResponseEntity<User> createAccount(@Valid @RequestBody UserResource user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createAccount(user));
    }

    @ApiOperation("Update account")
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<User> updateAccount(@PathVariable(name = "userId") Long userId,
                                              @Valid @RequestBody UserResource user,
                                              @RequestHeader("sessionID") UUID sessionID) {
        Long loginUser = Optional.of(loginService.getUser(sessionID)).get().getId();
        if (!loginUser.equals(userId))
            throw new WrongCredentialsException("Simple user can update only their account");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userService.updateAccount(user, userId));
    }

    @ApiOperation("Admin get all user profiles")
    @GetMapping(value = "/admin/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestHeader("sessionID") UUID sessionID,
                                                  Pageable pageable) {
        boolean isAdmin = loginService.getUser(sessionID).isAdmin();
        if (!isAdmin)
            throw new WrongCredentialsException("Only admin can see all accounts");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(isAdmin, pageable));
    }

    @ApiOperation("Admin get all user profiles order by number of order")
    @GetMapping(value = "/admin/users/sorted")
    public ResponseEntity<List<User>> getUsersSorted(@RequestHeader("sessionID") UUID sessionID) {
        boolean isAdmin = loginService.getUser(sessionID).isAdmin();
        if (!isAdmin)
            throw new WrongCredentialsException("Only admin can see all accounts");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllOrderByNumberOFOrders(isAdmin));
    }

    @ApiOperation("Admin create user")
    @PostMapping(value = "/admin/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserResource user,
                                           @RequestHeader("sessionID") UUID sessionID) {
        boolean isAdmin = loginService.getUser(sessionID).isAdmin();
        if (!isAdmin)
            throw new WrongCredentialsException("Only admin can create accounts");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.adminCreateUser(user, isAdmin));
    }

    @ApiOperation("Admin update user")
    @PutMapping(value = "/admin/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "userId") Long userId,
                                           @Valid @RequestBody UserResource user,
                                           @RequestHeader("sessionID") UUID sessionID) {
        boolean isAdmin = loginService.getUser(sessionID).isAdmin();
        if (!isAdmin)
            throw new WrongCredentialsException("Only admin can update accounts");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userService.adminUpdateUser(user, isAdmin, userId));
    }

    @ApiOperation("Admin delete user")
    @DeleteMapping(value = "/admin/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable(name = "userId") Long userId,
                                     @RequestHeader("sessionID") UUID sessionID) {
        boolean isAdmin = loginService.getUser(sessionID).isAdmin();
        if (!isAdmin)
            throw new WrongCredentialsException("Only admin can delete accounts");
        userService.adminDeleteUser(userId, isAdmin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
