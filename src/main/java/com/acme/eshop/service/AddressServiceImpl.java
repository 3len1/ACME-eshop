package com.acme.eshop.service;

import com.acme.eshop.domain.Address;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public Address getProduct(int id) {
        return null;
    }

    @Override
    public Address updateProduct(int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public Address createProduct() {
        return null;
    }
}
