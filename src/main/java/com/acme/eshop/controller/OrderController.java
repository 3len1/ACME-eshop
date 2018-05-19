package com.acme.eshop.controller;

import com.acme.eshop.domain.Order;
import com.acme.eshop.service.LoginService;
import com.acme.eshop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Here you can control your orders", tags = "Orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private LoginService loginService;
    //generally we use @Request params

    @ApiOperation(value = "Get all orders")
    @GetMapping(value = "/orders")
    public ResponseEntity<Page<Order>> getAllOrder(Long userId, Pageable pageable) {
        //check if user has an open season if not 401
        //you do not need userId you need token @requestheader, and get user if null 401 if not call
        //user.getId() instead of userId
        return ResponseEntity.ok()
                .body(orderService.getAllOrder(userId, pageable));
    }

    @ApiOperation(value = "Get order by user")
    @GetMapping(value = "/orders/{userId}")
    public ResponseEntity<Page<Order>> getAllByUser(@PathVariable(name = "userId") Long userId, Pageable pageable) {
        //you take token, take user if null -> 401 check is admin if not 403
        //if getAllByUser return null send 404
        return ResponseEntity.ok()
                .body(orderService.getAllByUser(userId, pageable));
    }
}
