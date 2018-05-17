package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import com.acme.eshop.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User showUser(Long userId);

    User createAccount(UserDto user);

    User updateAccount(UserDto user, Long userId);

    User adminCreateUser(UserDto user, boolean isAdmin);

    User adminUpdateUser(UserDto user, boolean isAdmin);

    void adminDeleteUser(Long userId, boolean isAdmin);

    Page<User> getAll(boolean isAdmin, Pageable pageable);

    List<User> getAllOrderByNumberOFOrders(boolean isAdmin);

}
