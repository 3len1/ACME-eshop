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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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


    @Transactional
    @Override
    public User createAccount(UserResource user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.warn("User with email [{}] already exist", user.getEmail());
            return null;
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setCreatedDate(DateUtils.epochNow());
            retrieveUser.setAdmin(false);
            retrieveUser = userRepository.save(retrieveUser);
            Cart cart = new Cart();
            cart.setUser(retrieveUser);
            cartRepository.save(cart);
            addressService.createUserAddress(user.getAddress(), retrieveUser);
            log.info("User [{}] created", user.getEmail());
        }
        return userRepository.findByEmail(user.getEmail());
    }


    @Transactional
    @Override
    public User updateAccount(UserResource user, Long userId) {
        if (!userRepository.findById(userId).isPresent()) {
            log.warn("User [{}] doesn't exist", userId);
            return null;
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setEmail(userRepository.findById(userId).get().getEmail());
            userRepository.save(retrieveUser);
            retrieveUser.setAdmin(false);
            addressService.updateUserAddress(userRepository.findByEmail(user.getEmail()).getId(), user.getAddress());
            log.info("User account[{}] update", retrieveUser.getEmail());
        }
        return userRepository.findByEmail(user.getEmail());
    }


    @Transactional
    @Override
    public User adminCreateUser(UserResource user, boolean isAdmin) {
        if (!isAdmin && (userRepository.findByEmail(user.getEmail()) != null)) {
            log.warn("User with email [{}] already exist", user.getEmail());
            return null;
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setCreatedDate(DateUtils.epochNow());
            retrieveUser = userRepository.save(retrieveUser);
            Cart cart = new Cart();
            cart.setUser(retrieveUser);
            cartRepository.save(cart);
            addressService.createUserAddress(user.getAddress(), retrieveUser);
            log.info("Admin create create [{}]", user.getEmail());
        }
        return userRepository.findByEmail(user.getEmail());
    }


    @Transactional
    @Override
    public User adminUpdateUser(UserResource user, boolean isAdmin) {
        if (!isAdmin && (userRepository.findByEmail(user.getEmail()) == null)) {
            log.warn("User [{}] doesn't exist", user.getEmail());
            return null;
        }
        User retrieveUser;
        retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            userRepository.save(retrieveUser);
            addressService.updateUserAddress(userRepository.findByEmail(user.getEmail()).getId(), user.getAddress());
            log.info("Admin update user [{}]", retrieveUser.getEmail());
        }
        return userRepository.findByEmail(user.getEmail());
    }


    @Transactional
    @Override
    public void adminDeleteUser(Long userId, boolean isAdmin) {
        userRepository.findById(userId).ifPresent(user -> {
            if (isAdmin) {
                //TODO Check is items are still
                orderRepository.deleteAllByUser(user);
                cartRepository.deleteAllByUser(user);
                addressRepository.delete(user.getAddress());
                userRepository.deleteById(userId);
                log.info("Admin delete user's [{}] update", userId);
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
