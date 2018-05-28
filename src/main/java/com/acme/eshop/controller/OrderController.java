package com.acme.eshop.controller;

import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.WrongCredentialsException;
import com.acme.eshop.resources.ItemResource;
import com.acme.eshop.resources.OrderResource;
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
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@Api(description = "Order Controller", tags = "Orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Get orders", notes = "Simple user see only his own orders, admin see all orders")
    @GetMapping(value = "/orders")
    public ResponseEntity<Page<Order>> getAllOrder(@RequestHeader("sessionID") UUID sessionID, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllOrder(loginService.getUser(sessionID).getId(), pageable));
    }

    @ApiOperation(value = "Get orders by user", notes = "Admin can check specific user orders")
    @GetMapping(value = "/{userId}/orders")
    public ResponseEntity<Page<Order>> getAllByUser(@PathVariable(name = "userId") Long userId,
                                                    @RequestHeader("sessionID") UUID sessionID, Pageable pageable) {
        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can see other user orders");
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllByUser(userId, pageable));
    }

    @ApiOperation(value = "Get single order")
    @GetMapping(value = "/orders/{orderCode}")
    public ResponseEntity<Order> getOrder(@PathVariable(name = "orderCode") String orderCode,
                                          @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can see order");
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.showOrder(orderCode, user.getId()));
    }

    @ApiOperation(value = "Get all items from order")
    @GetMapping(value = "/orders/{orderCode}/items")
    public ResponseEntity<List<Item>> getAllItemsFromOrder(@PathVariable(name = "orderCode") String orderCode,
                                                           @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can see items from order");
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllItemsFromOrder(orderCode, user.getId()));
    }

    @ApiOperation(value = "Create an Order")
    @PostMapping(value = "/orders")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderResource order, @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can create order");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(order, user.getId()));
    }


    @ApiOperation(value = "Pay an order")
    @PutMapping(value = "/orders/{orderCode}")
    public ResponseEntity<Order> payOrder(@PathVariable(name = "orderCode") String orderCode,
                                          @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can pay order");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(orderService.payOrder(orderCode, user.getId()));
    }

    @ApiOperation(value = "Cancel and order")
    @PutMapping(value = "/orders/{orderCode}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable(name = "orderCode") String orderCode,
                                              @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can cancel order");
        if (orderService.cancelOrder(orderCode, user.getId()))
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order canceled");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Order can't cancel because is payed");
    }

    @ApiOperation(value = "Admin delete an Order")
    @DeleteMapping(value = "admin/orders/{orderCode}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "orderCode") String orderCode,
                                              @RequestHeader("sessionID") UUID sessionID) {
        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can delete an order");
        orderService.deleteOrder(orderCode, loginService.getUser(sessionID).getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order deleted");
    }

    @ApiOperation(value = "Remove an item from order")
    @DeleteMapping(value = "/orders/{orderCode}/items/{productCode}")
    public ResponseEntity<Order> removeItemFromOrder(@PathVariable(name = "orderCode") String orderCode,
                                                     @PathVariable(name = "productCode") String productCode,
                                                     @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can remove items from order");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(orderService.removeItemFromOrder(orderCode, productCode, user.getId()));

    }

    @ApiOperation(value = "Add items to order")
    @PostMapping(value = "/orders/{orderCode}/items")
    public ResponseEntity<Order> addItemsToOrder(@Valid @RequestBody ItemResource item,
                                                 @PathVariable String orderCode,
                                                 @RequestHeader("sessionID") UUID sessionID) {
        User user = loginService.getUser(sessionID);
        if (user == null)
            throw new WrongCredentialsException("Only login user can remove items from order");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.addItemsToOrder(orderCode, item, user.getId()));

    }

}
