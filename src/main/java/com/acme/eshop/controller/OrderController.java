package com.acme.eshop.controller;

import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.Order;
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

    @ApiOperation(value = "Get all orders")
    @GetMapping(value = "/orders")
    public ResponseEntity<Page<Order>> getAllOrder(@RequestHeader("sessionID") UUID sessionID, Pageable pageable) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .body(orderService.getAllOrder(loginService.getUser(sessionID).getId(), pageable));
    }

    @ApiOperation(value = "Get orders by user")
    @GetMapping(value = "/{userId}/orders")
    public ResponseEntity<Page<Order>> getAllByUser(@PathVariable Long userId,
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
    @GetMapping(value = "/orders/{orderCode}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderCode,
                                          @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .body(orderService.showOrder(orderCode, loginService.getUser(sessionID).getId()));
    }

    @ApiOperation(value = "Get all items from order")
    @GetMapping(value = "/{orderCode}/items")
    public ResponseEntity<List<Item>> getAllItemsFromOrder(@PathVariable String orderCode,
                                                          @RequestHeader("sessionID") UUID sessionID) {
        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .body(orderService.getAllItemsFromOrder(orderCode, loginService.getUser(sessionID).getId()));
    }

    @ApiOperation(value = "Create an Order")
    @PostMapping(value = "/orders")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderResource order, @RequestHeader("sessionID") UUID sessionID) {
        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(order, loginService.getUser(sessionID).getId()));

    }

    @ApiOperation(value = "Delete an Order")
    @DeleteMapping(value = "/orders/{orderCode}")
    public ResponseEntity deleteOrder(@RequestHeader("sessionID") UUID sessionID, @PathVariable String orderCode) {
        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        orderService.deleteOrder(orderCode, loginService.getUser(sessionID).getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Remove an item from order")
    @DeleteMapping(value = "/orders/{orderCode}/items/{productCode}")
    public ResponseEntity removeItemFromOrder(@PathVariable String orderCode,
                                              @PathVariable String productCode,
                                              @RequestHeader("sessionID") UUID sessionID) {
        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        orderService.removeItemFromOrder(orderCode, productCode, loginService.getUser(sessionID).getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Add items to order")
    @PostMapping(value = "/orders/{orderCode}/items")
    public ResponseEntity<Order> addItemsToOrder(@Valid @RequestBody ItemResource item,
                                                 @PathVariable String orderCode,
                                                 @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.addItemsToOrder(orderCode, item, loginService.getUser(sessionID).getId()));

    }

    @ApiOperation(value = "Pay the order")
    @PutMapping(value = "/orders/{orderCode}")
    public ResponseEntity<Order> payOrder(@PathVariable String orderCode,
                                          @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.payOrder(orderCode, loginService.getUser(sessionID).getId()));
    }

    @ApiOperation(value = "Cancel order")
    @PutMapping(value = "/orders/{orderCode}/cancel")
    public ResponseEntity calncelOrder(@PathVariable String orderCode,
                                       @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.cancelOrder(orderCode, loginService.getUser(sessionID).getId()));
    }
}
