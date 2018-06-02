package com.acme.eshop.repository;

import com.acme.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByToken(UUID token);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User save(User user);
    void delete(User user);
}
