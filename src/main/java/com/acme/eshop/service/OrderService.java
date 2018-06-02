package com.acme.eshop.service;

import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.OrderItem;
import com.acme.eshop.dto.UserCountDto;
import com.acme.eshop.resources.ItemResource;
import com.acme.eshop.resources.OrderResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface OrderService {

    Page<Order> getAllOrder(Long userId, Pageable pageable);
    Page<Order> getAllByUser(Long userId, Pageable pageable);
    Order showOrder(String orderCode, Long userId);
    Order payOrder(String orderCode, Long userId);
    Order createOrder(OrderResource orderResource, Long userId);
    Order addItemsToOrder(String orderCode, ItemResource addedItem, Long userId);
    Order removeItemFromOrder(String orderCode, String productCode, Long userId);
    boolean cancelOrder(String orderCode, Long userId);
    void deleteOrder(String orderCode, Long userId);
    List<OrderItem> getAllItemsFromOrder(String orderCode, Long userId);
}
