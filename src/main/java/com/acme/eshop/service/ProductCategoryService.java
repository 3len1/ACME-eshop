package com.acme.eshop.service;

import com.acme.eshop.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    public List<ProductCategory> getAll();
    public ProductCategory getProductCategory(int id);
    public ProductCategory updateProductCategory(int id);
    public void deleteProductCategory(int id);
    public ProductCategory createProductCategory();

}
