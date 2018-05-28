package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.NotIdenticalUserException;
import com.acme.eshop.exceptions.WrongCredentialsException;
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
        if (users.size() == 1)
            return users.get(0);
        if (users.isEmpty()) throw new WrongCredentialsException("User haven't login yet");
        log.error("There are more than one user log in [{}]", token);
        throw new NotIdenticalUserException("User is not identical");
    }

    @Transactional
    @Override
    public User logIn(String email, String password) {
        if (email == null && password == null)
            throw new WrongCredentialsException("You must give email and password for login");
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            user.setToken(UUID.randomUUID());
            log.info("User [{}} log in successfully", user.getId());
            return userRepository.save(user);
        }
        log.warn("Credentials email [{}] and password [{}] are not correct", email, password);
        throw new WrongCredentialsException("Credentials are not correct");
    }

    @Transactional
    @Override
    public void logOut(UUID token) {
        List<User> users = userRepository.findByToken(token);
        if (users.size() == 1) {
            users.get(0).setToken(null);
            userRepository.save(users.get(0));
            log.info("User [{}} log out successfully ", users.get(0).getId());
        } else if (users.size() > 1) {
            log.warn("Token [{}} is not unique", token);
            throw new NotIdenticalUserException("User is not identical");
        } else {
            log.warn("Token [{}] is not valid", token);
            throw new WrongCredentialsException("User haven't login yet");
        }
    }
}
