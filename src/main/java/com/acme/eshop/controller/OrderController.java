package com.acme.eshop.controller;

import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.Order;
import com.acme.eshop.service.LoginService;
import com.acme.eshop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@Api(description = "Here you can control your orders", tags = "Orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Get all orders")
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<Page<Order>> getAllOrder(@RequestHeader("sessionID") UUID sessionID, Pageable pageable) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .body(orderService.getAllOrder(loginService.getUser(sessionID).getId(), pageable));
    }

    @ApiOperation(value = "Get order by user")
    @RequestMapping(value = "/{userId}/orders", method = RequestMethod.GET)
    public ResponseEntity<Page<Order>> getAllByUser(@PathVariable(name = "userId") Long userId,
                                                    @RequestHeader("sessionID") UUID sessionID, Pageable pageable) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(!loginService.getUser(sessionID).isAdmin())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return ResponseEntity.ok()
                .body(orderService.getAllByUser(userId, pageable));
    }

    @ApiOperation(value = "Get single order")
    @RequestMapping(value = "/orders/{orderCode}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable(name = "orderCode") String orderCode,
                                          @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .body(orderService.showOrder(orderCode, loginService.getUser(sessionID).getId()));
    }

    @ApiOperation(value = "Get all items from order")
    @RequestMapping(value = "/{orderCode}/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getAllItemsFromOrder(@PathVariable(name = "orderCode") String orderCode,
                                                          @RequestHeader("sessionID") UUID sessionID) {
        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .body(orderService.getAllItemsFromOrder(orderCode, loginService.getUser(sessionID).getId()));
    }


}
