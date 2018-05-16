package com.acme.eshop.service;

import com.acme.eshop.domain.Product;

import com.acme.eshop.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface ProductService {

   Product showProduct(String productCode);
   Product createProduct(ProductDto product);
   Product updateProduct(ProductDto product);
   void deleteProduct(ProductDto product);
   Page<Product> getAll(Pageable pageable);
   Page<Product> mostPurchasedByCategory(String categoryName, Pageable pageable);
   Page<Product> productByCategory(String categoryName, Pageable pageable);
   Page<Product> search(String categoryName, BigDecimal priceMin, BigDecimal priceMax, Integer purchasedMin, Integer purchasedMax, Pageable pageable);
   List<Product> outOfStock();
   List<Product> mostPurchased();

}
