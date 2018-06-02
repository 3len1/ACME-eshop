package com.acme.eshop.service;

import com.acme.eshop.domain.Product;

import com.acme.eshop.resources.ProductResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService {

    Product showProduct(String productCode);
    Product createProduct(ProductResource product);
    Product updateProduct(ProductResource product);
    void deleteProduct(String productCode);
    Page<Product> getAll(Pageable pageable);
    Page<Product> mostPurchasedByCategory(String categoryName, Pageable pageable);
    Page<Product> productByCategory(String categoryName, Pageable pageable);
    Page<Product> search(String categoryName, Long priceMin, Long priceMax, Integer purchasedMin, Integer purchasedMax, Pageable pageable);
    List<Product> outOfStock();
    List<Product> mostPurchased();

}
