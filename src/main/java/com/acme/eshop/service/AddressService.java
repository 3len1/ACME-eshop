package com.acme.eshop.service;

import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import com.acme.eshop.resources.AddressResource;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address getUserAddress(Long userId);

    Address updateUserAddress(Long userId, AddressResource addressResource);

    Address createUserAddress(AddressResource addressResource, User user);

}
