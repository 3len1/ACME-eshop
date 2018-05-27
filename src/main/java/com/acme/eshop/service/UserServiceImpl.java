package com.acme.eshop.service;

import com.acme.eshop.converter.UserConverter;
import com.acme.eshop.domain.Cart;
import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.ResourceNotValid;
import com.acme.eshop.exceptions.UserAlreadyExistException;
import com.acme.eshop.exceptions.UserNotFoundException;
import com.acme.eshop.exceptions.WrongCredentialsException;
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
            throw new UserAlreadyExistException("User already exist");
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setCreatedDate(DateUtils.epochNow());
            retrieveUser.setAdmin(false);
            Cart cart = new Cart();
            cart.setUser(retrieveUser);
            cartRepository.save(cart);
            addressService.createUserAddress(user.getAddress(), retrieveUser);
            log.info("User [{}] created", user.getEmail());
            return userRepository.save(retrieveUser);
        }
        throw new ResourceNotValid("User resource is not valid, try again");
    }


    @Transactional
    @Override
    public User updateAccount(UserResource user, Long userId) {
        if (!userRepository.findById(userId).isPresent()) {
            log.warn("User [{}] doesn't exist", userId);
            throw new UserNotFoundException("User does not exist");
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setEmail(userRepository.findById(userId).get().getEmail());
            retrieveUser.setAdmin(false);
            addressService.updateUserAddress(userRepository.findByEmail(user.getEmail()).getId(), user.getAddress());
            log.info("User account[{}] update", retrieveUser.getEmail());
            return userRepository.save(retrieveUser);
        }
        throw new ResourceNotValid("User resource is not valid, try again");
    }


    @Transactional
    @Override
    public User adminCreateUser(UserResource user, boolean isAdmin) {
        if (!isAdmin) throw new WrongCredentialsException("You are not aloud to make other accounts");
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.warn("User with email [{}] already exist", user.getEmail());
            throw new UserAlreadyExistException("This user already exist");
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            retrieveUser.setCreatedDate(DateUtils.epochNow());
            Cart cart = new Cart();
            cart.setUser(retrieveUser);
            cartRepository.save(cart);
            addressService.createUserAddress(user.getAddress(), retrieveUser);
            log.info("Admin create create [{}]", user.getEmail());
            return userRepository.save(retrieveUser);
        }
        throw new ResourceNotValid("User resource is not valid, try again");
    }


    @Transactional
    @Override
    public User adminUpdateUser(UserResource user, boolean isAdmin) {
        if (!isAdmin) throw new WrongCredentialsException("You are not aloud to make other accounts");
        if (userRepository.findByEmail(user.getEmail()) == null){
            log.warn("User [{}] doesn't exist", user.getEmail());
            throw new UserNotFoundException("This user does not exist");
        }
        User retrieveUser = userConverter.getUser(user);
        if (retrieveUser != null) {
            addressService.updateUserAddress(userRepository.findByEmail(user.getEmail()).getId(), user.getAddress());
            log.info("Admin update user [{}]", retrieveUser.getEmail());
            return userRepository.save(retrieveUser);

        }
        throw new ResourceNotValid("User resource is not valid, try again");
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
            else{
                throw new WrongCredentialsException("You are not aloud to make other accounts");
            }
        });
    }

    @Override
    public Page<User> getAll(boolean isAdmin, Pageable pageable) {
        if(!isAdmin) throw new WrongCredentialsException("You are not aloud to see other account");
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> getAllOrderByNumberOFOrders(boolean isAdmin) {
        //TODO check if it work i am not sure at all is there any better way
        Map<Integer, User> orderedUsers = new TreeMap<>();
        if (!isAdmin) throw new WrongCredentialsException("You are not aloud to see other account");
        userRepository.findAll().forEach(user -> orderedUsers.put(orderRepository.countByUser(user), user));
        List<User> users = new ArrayList<>();
        orderedUsers.forEach((k, v) -> users.add(orderedUsers.get(k)));
        return users;
    }
}
