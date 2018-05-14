package com.acme.eshop.service;

import com.acme.eshop.domain.ProductCategory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("productCategoryService")
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    @Override
    public ProductCategory getProductCategory(int id) {
        return null;
    }

    @Override
    public ProductCategory updateProductCategory(int id) {
        return null;
    }

    @Override
    public void deleteProductCategory(int id) {

    }

    @Override
    public ProductCategory createProductCategory() {
        return null;
    }
}
