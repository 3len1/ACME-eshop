package com.acme.eshop.repository;

import com.acme.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    User findByToken(UUID token);

    User findByEmailAndPassword(String email, String password);



}
