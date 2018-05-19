package com.acme.eshop.service;

import com.acme.eshop.converter.AddressConverter;
import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import com.acme.eshop.resources.AddressResource;
import com.acme.eshop.repository.AddressRepository;
import com.acme.eshop.repository.UserRepository;
import com.acme.eshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;


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
        User user = userRepository.findById(userId).orElseGet(null);
        return (user != null) ? addressRepository.findByUser(user) : null;
    }

    @Transactional
    @Override
    public Address updateUserAddress(Long userId, AddressResource addressResource) {
        User user = userRepository.findById(userId).orElseGet(null);
        Address address = addressConverter.getAddress(addressResource);
        if(user == null) log.warn("User [{}] does not exist" , userId);
        else if (address == null) log.warn("Address for user [{}] can't be updated" , userId);
        else {
            address.setId(user.getAddress().getId());
            log.info("Address for user [{}] updated", userId);
        }
        return (user != null && address != null) ? addressRepository.save(address) : null;
    }

    @Transactional
    @Override
    public Address createUserAddress(AddressResource addressResource, User user) {
        Address address = addressConverter.getAddress(addressResource);
        address.setCreatedDate(DateUtils.epochNow());
        address.setUser(user);
        log.info("Address for user [{}] created",user.getId());
        return addressRepository.save(address);
    }
}
