package com.acme.eshop.repository;

import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser(User user);
}
