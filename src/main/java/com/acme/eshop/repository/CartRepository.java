package com.acme.eshop.repository;


import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Eleni on 14/5/2018.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart save(Cart cart);

    void deleteAllByUser(User user);

}
