package com.acme.eshop.service;

import com.acme.eshop.domain.Product;

import com.acme.eshop.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

   public Product showProduct(String productCode);
   public Product createProduct(ProductDto product);
   public Product updateProduct(ProductDto product);
   public void deleteProduct(ProductDto product);
   public void deleteProductByCategory(String categoryName);
   public List<Product> mostPurchased();
   public Page<Product> mostPurchasedByCategory(String categoryName, Pageable pageable);
   public List<Product> outOfStock();
   public Page<Product> productByCategory(String categoryName, Pageable pageable);
   public Page<Product> search(String categoryName, BigDecimal priceMin, BigDecimal priceMax, Integer purchasedMin, Integer purchasedMax, Pageable pageable);


}
