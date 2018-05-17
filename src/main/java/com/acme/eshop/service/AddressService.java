package com.acme.eshop.service;

import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import com.acme.eshop.dto.AddressDto;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address getUserAddress(Long userId);

    Address updateUserAddress(Long userId, AddressDto addressDto);

    Address createUserAddress(AddressDto addressDto, User user);

}
