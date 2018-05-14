package com.acme.eshop.service;

import com.acme.eshop.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {


    public List<Order> getAll();
    public Order getOrder(int id);
    public Order updateOrder(int id);
    public void deleteOrder(int id);
    public Order createOrder();

}
