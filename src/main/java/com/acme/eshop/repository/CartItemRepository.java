package com.acme.eshop.repository;

import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.CartItem;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eleni on 6/2/2018.
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem save(CartItem item);
    void delete(CartItem item);;
    void deleteAllByCart(Cart cart);
    void deleteAllByProductAndCart(Product product, Cart cart);
    List<CartItem> findByCart(Cart cart);
}