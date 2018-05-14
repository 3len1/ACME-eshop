package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getAll();
    public User getUser(int id);
    public User updateUser(int id);
    public void deleteUser(int id);
    public User createUser();

    public User userLogin();
    public void userLogout(User user);

}
