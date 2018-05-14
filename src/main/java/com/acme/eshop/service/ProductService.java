package com.acme.eshop.service;

import com.acme.eshop.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

   public List<Product> getAll();
   public Product getProduct(int id);
   public Product updateProduct(int id);
   public void deleteProduct(int id);
   public Product createProduct();


}
