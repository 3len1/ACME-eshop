package com.acme.eshop.repository;

import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order save(Order order);
    void delete(Order order);
    void deleteAllByUser(User user);

    Page<Order> findByUser(User user, Pageable pageable);
    Page<Order> findAll(Pageable pageable);

    @Query("select count(o) from Order o where o.user = ?1")
    int countByUser(@Param("user") User user);

}
