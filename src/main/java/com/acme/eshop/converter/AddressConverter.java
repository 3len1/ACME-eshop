package com.acme.eshop.converter;

import com.acme.eshop.domain.Address;
import com.acme.eshop.dto.AddressDto;
import javaslang.control.Try;
import org.springframework.stereotype.Component;

/**
 * Created by Eleni on 16/5/2018.
 */
@Component
public class AddressConverter {

    public Address getAddress(AddressDto addressDto){
        return Try.of(() -> {
            Address address = new Address();
            address.setPostalCode(addressDto.getPostalCode());
            address.setTown(addressDto.getTown());
            address.setStreet(addressDto.getStreet());
            return address;
        }).getOrElseGet(null);
    }
}
