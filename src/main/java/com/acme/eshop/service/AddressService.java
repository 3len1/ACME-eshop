package com.acme.eshop.service;

import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import com.acme.eshop.resources.AddressResource;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address getUserAddress(Long userId);
    Address updateUserAddress(AddressResource addressResource, Long UserId);
    Address createUserAddress(AddressResource addressResource, Long userId);

}
