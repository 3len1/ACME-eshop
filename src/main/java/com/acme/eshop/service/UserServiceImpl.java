package com.acme.eshop.service;

import com.acme.eshop.converter.UserConverter;
import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.User;
import com.acme.eshop.resources.UserResource;
import com.acme.eshop.repository.AddressRepository;
import com.acme.eshop.repository.CartRepository;
import com.acme.eshop.repository.OrderRepository;
import com.acme.eshop.repository.UserRepository;
import com.acme.eshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserConverter userConverter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public User showUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User createAccount(UserResource user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            return null;
        User retrieveUser;

        retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setCreatedDate(DateUtils.epochNow());
            retrieveUser.setAdmin(false);
            retrieveUser = userRepository.save(retrieveUser);
            Cart cart = new Cart();
            cart.setUser(retrieveUser);
            cartRepository.save(cart);
            addressService.createUserAddress(user.getAddress(), retrieveUser);
        }

        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public User updateAccount(UserResource user, Long userId) {
        if (!userRepository.findById(userId).isPresent())
            return null;
        User retrieveUser;
        retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            userRepository.save(retrieveUser);
            retrieveUser.setAdmin(false);
            addressService.updateUserAddress(userRepository.findByEmail(user.getEmail()).getId(), user.getAddress());
        }
        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public User adminCreateUser(UserResource user, boolean isAdmin) {
        if (!isAdmin && (userRepository.findByEmail(user.getEmail()) != null))
            return null;
        User retrieveUser;
        retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setCreatedDate(DateUtils.epochNow());
            retrieveUser = userRepository.save(retrieveUser);
            Cart cart = new Cart();
            cart.setUser(retrieveUser);
            cartRepository.save(cart);
            addressService.createUserAddress(user.getAddress(), retrieveUser);
        }
        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public User adminUpdateUser(UserResource user, boolean isAdmin) {
        if (isAdmin == false && (userRepository.findByEmail(user.getEmail()) == null))
            return null;
        User retrieveUser;
        retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            userRepository.save(retrieveUser);
            addressService.updateUserAddress(userRepository.findByEmail(user.getEmail()).getId(), user.getAddress());
        }
        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public void adminDeleteUser(Long userId, boolean isAdmin) {
        userRepository.findById(userId).ifPresent(user -> {
            if (isAdmin) {
                //TODO Check is items are still
                orderRepository.deleteAllByUser(user);
                cartRepository.deleteAllByUser(user);
                addressRepository.delete(user.getAddress());
                userRepository.deleteById(userId);
            }
        });
    }

    @Override
    public Page<User> getAll(boolean isAdmin, Pageable pageable) {
        return isAdmin ? userRepository.findAll(pageable) : null;
    }

    @Override
    public List<User> getAllOrderByNumberOFOrders(boolean isAdmin) {
        //TODO check if it work i am not sure at all is there any better way
        Map<Integer, User> orderedUsers = new TreeMap<>();
        if (!isAdmin) return null;
        userRepository.findAll().stream().forEach(user -> {
            orderedUsers.put(orderRepository.countByUser(user), user);
        });
        List<User> users = new ArrayList<>();
        orderedUsers.forEach((k, v) -> users.add(orderedUsers.get(k)));
        return users;
    }
}
