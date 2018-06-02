package com.acme.eshop.service;

import com.acme.eshop.domain.User;
import com.acme.eshop.dto.UserCountDto;
import com.acme.eshop.resources.UserResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    User showUser(Long userId);
    User createAccount(UserResource user);
    User updateAccount(UserResource user, Long userId);
    User adminCreateUser(UserResource user, boolean isAdmin);
    User adminUpdateUser(UserResource user, boolean isAdmin, Long userId);
    void adminDeleteUser(Long userId, boolean isAdmin);
    Page<User> getAll(boolean isAdmin, Pageable pageable);
    List<UserCountDto> getAllOrderByNumberOFOrders(boolean isAdmin);

}
