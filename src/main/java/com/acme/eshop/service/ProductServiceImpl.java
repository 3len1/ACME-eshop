package com.acme.eshop.service;

import com.acme.eshop.domain.Product;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("productService")
@Transactional
public class ProductServiceImpl implements ProductService {


    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public Product updateProduct(int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public Product createProduct() {
        return null;
    }
}
