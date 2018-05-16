package com.acme.eshop.service;

import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.repository.ProductCategoryRepository;
import com.acme.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Component("productCategoryService")
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductCategory getProductCategory(String categoryName) {
        return productCategoryRepository.findByName(categoryName);
    }

    @Override
    public ProductCategory createProductCategory(String categoryName) {
        if (productCategoryRepository.findByName(categoryName)!= null)
            return null;
        ProductCategory category = new ProductCategory();
        category.setCreatedDate(Instant.now().toEpochMilli());
        category.setName(categoryName);
        return productCategoryRepository.save(category);
    }

    @Override
    public ProductCategory updateProductCategory(String categoryName) {
        ProductCategory category  =productCategoryRepository.findByName(categoryName);
        if (category!= null)
            return productCategoryRepository.save(category);
        return null;
    }

    @Override
    public void deleteProductCategory(String categoryName) {
        ProductCategory category  =productCategoryRepository.findByName(categoryName);
        if (category!= null){
          productRepository.deleteByCategory(category);
          productCategoryRepository.delete(category);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }
}
