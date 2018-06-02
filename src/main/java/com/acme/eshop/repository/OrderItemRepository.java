package com.acme.eshop.repository;

import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.OrderItem;
import com.acme.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eleni on 6/2/2018.
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem save(OrderItem item);
    void delete(OrderItem item);
    void deleteAllByOrder(Order order);
    void deleteAllByProductAndOrder(Product product, Order order);
    OrderItem findOneByProductAndOrder(Product product, Order order);
    List<OrderItem> findByOrder(Order order);
}
