package com.acme.eshop.service;

import com.acme.eshop.domain.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        return null;
    }

    @Override
    public Order updateOrder(int id) {
        return null;
    }

    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public Order createOrder() {
        return null;
    }
}
