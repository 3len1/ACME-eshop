package com.acme.eshop.service;

import com.acme.eshop.domain.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {

    ProductCategory getProductCategory(String categoryName);

    ProductCategory createProductCategory(String categoryName);

    ProductCategory updateProductCategory(String categoryName);

    void deleteProductCategory(String categoryName);

    List<ProductCategory> getAll();


}
