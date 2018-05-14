package com.acme.eshop.service;

import com.acme.eshop.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AddressService {

    public List<Address> getAll();
    public Address getProduct(int id);
    public Address updateProduct(int id);
    public void deleteProduct(int id);
    public Address createProduct();

}
