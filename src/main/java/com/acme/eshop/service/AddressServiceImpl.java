package com.acme.eshop.service;

import com.acme.eshop.converter.AddressConverter;
import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import com.acme.eshop.exceptions.NotIdenticalAddressForUser;
import com.acme.eshop.exceptions.ResourceNotValid;
import com.acme.eshop.exceptions.UserNotFoundException;
import com.acme.eshop.resources.AddressResource;
import com.acme.eshop.repository.AddressRepository;
import com.acme.eshop.repository.UserRepository;
import com.acme.eshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;


@Component("addressService")
public class AddressServiceImpl implements AddressService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressConverter addressConverter;

    @Override
    public Address getUserAddress(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            log.warn("User [{}] not exist", userId);
            throw new UserNotFoundException("User not found");
        }
        return user.getAddress();
    }

    @Transactional
    @Override
    public Address updateUserAddress(AddressResource addressResource, Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (user == null) {
            log.warn("User [{}] does not exist", userId);
            throw new UserNotFoundException("User not found");
        }
        Address oldAddresses = this.getUserAddress(userId);
        Address address = addressConverter.getAddress(addressResource);
        if (address == null) {
            log.warn("Address for user [{}] can't be updated", userId);
            throw new ResourceNotValid("Address is not valid");
        } else {
            address.setId(oldAddresses.getId());
            log.info("Address for user [{}] updated", userId);
        }
        return addressRepository.save(address);
    }

    @Transactional
    @Override
    public Address createUserAddress(AddressResource addressResource, Long userId) {
        Address address = addressConverter.getAddress(addressResource);
        if (address == null) {
            log.warn("Address for user [{}] can't be updated", userId);
            throw new ResourceNotValid("Address is not valid");
        }
        address.setCreatedDate(DateUtils.epochNow());
        log.info("Address for user [{}] created", userId);
        return addressRepository.save(address);
    }
}
