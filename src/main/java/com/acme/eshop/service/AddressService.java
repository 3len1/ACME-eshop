package com.acme.eshop.service;

import com.acme.eshop.domain.Address;
import com.acme.eshop.resources.AddressResource;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address updateUserAddress(AddressResource addressResource, Long UserId);
    Address createUserAddress(AddressResource addressResource, Long userId);

}
