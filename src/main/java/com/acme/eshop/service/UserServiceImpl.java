package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Override
    public User userLogin() {
        return null;
    }

    @Override
    public void userLogout(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User updateUser(int id) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public User createUser() {
        return null;
    }
}
