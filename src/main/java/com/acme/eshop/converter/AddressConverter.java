package com.acme.eshop.converter;

import com.acme.eshop.domain.Address;
import com.acme.eshop.resources.AddressResource;
import javaslang.control.Try;
import org.springframework.stereotype.Component;

/**
 * Created by Eleni on 16/5/2018.
 */
@Component
public class AddressConverter {

    public Address getAddress(AddressResource addressResource) {
        return Try.of(() -> {
            Address address = new Address();
            address.setPostalCode(addressResource.getPostalCode());
            address.setTown(addressResource.getTown());
            address.setStreet(addressResource.getStreet());
            return address;
        }).get();
    }
}
