package com.acme.eshop.repository;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.Item;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    Item save(Item item);
    void delete(Item item);
    void deleteAllByProduct(Product product);
    void deleteAllByOrder(Order order);

    List<Item> findByOrOrder(Order order);
    List<Item> findByCart(Cart cart);
}
