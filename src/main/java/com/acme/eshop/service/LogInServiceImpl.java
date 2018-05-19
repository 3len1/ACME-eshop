package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import com.acme.eshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 11/5/2018.
 */
@Component("loginService")
public class LogInServiceImpl implements LoginService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(UUID token) {
        List<User> users = userRepository.findByToken(token);
        return (users.size() == 1) ? users.get(0) : null;
    }

    @Transactional
    @Override
    public User logIn(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            user.setToken(UUID.randomUUID());
            log.info("User [{}} log in successfully", user.getId());
            return user;
        }
        log.warn("Credentials email [{}] and password [{}] are nyo correct" , email , password);
        return null;
    }

    @Transactional
    @Override
    public void logOut(UUID token) {
        List<User> users = userRepository.findByToken(token);
        if (users.size() == 1)
            users.get(0).setToken(null);
        else if (users.size()> 1)
            log.warn("Token [{}} is not unique", token);
        else
            log.warn("Token [{}] is not valid", token);
    }
}
