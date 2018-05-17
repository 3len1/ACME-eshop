package com.acme.eshop.service;

import com.acme.eshop.converter.AddressConverter;
import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import com.acme.eshop.dto.AddressDto;
import com.acme.eshop.repository.AddressRepository;
import com.acme.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressConverter addressConverter;

    @Override
    public Address getUserAddress(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        return (user!=null)? addressRepository.findByUser(user): null;
    }

    @Override
    public Address updateUserAddress(Long userId, AddressDto addressDto) {
        User user = userRepository.findById(userId).orElseGet(null);
        Address address = addressConverter.getAddress(addressDto);
        address.setId(user.getAddress().getId());
        return (user!=null && address!=null)? addressRepository.save(address): null;
    }
}
