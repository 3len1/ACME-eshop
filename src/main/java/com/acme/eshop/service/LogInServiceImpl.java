package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import com.acme.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 11/5/2018.
 */
@Component("loginService")
@Transactional
public class LogInServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(UUID token) {
        List<User> users = userRepository.findByToken(token);
        return (users.size() == 1) ? users.get(0) : null;
    }

    @Override
    public User logIn(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null)
            user.setToken(UUID.randomUUID());
        return user;
    }

    @Override
    public void logOut(UUID token) {
        List<User> users = userRepository.findByToken(token);
        if (users.size() == 1)
            users.get(0).setToken(null);
    }
}
