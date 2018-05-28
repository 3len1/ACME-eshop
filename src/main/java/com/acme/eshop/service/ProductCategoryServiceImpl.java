package com.acme.eshop.service;

import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.exceptions.CategoryAlreadyExistException;
import com.acme.eshop.exceptions.CategoryNotFoundException;
import com.acme.eshop.repository.ProductCategoryRepository;
import com.acme.eshop.repository.ProductRepository;
import com.acme.eshop.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductCategory getProductCategory(String categoryName) {
        return productCategoryRepository.findByName(categoryName);
    }


    @Transactional
    @Override
    public ProductCategory createProductCategory(String categoryName) {
        if (productCategoryRepository.findByName(categoryName) != null) {
            log.warn("Category [{}] already exist", categoryName);
            throw new CategoryAlreadyExistException("Category already exist");
        }
        ProductCategory category = new ProductCategory();
        category.setCreatedDate(DateUtils.epochNow());
        category.setName(categoryName);
        log.info("Admin create new category [{}]", categoryName);
        return productCategoryRepository.save(category);
    }


    @Transactional
    @Override
    public ProductCategory updateProductCategory(String categoryName) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        if (category != null) {
            log.info("Admin update category [{}]", categoryName);
            return productCategoryRepository.save(category);
        }
        log.warn("Category [{}] does not exist", categoryName);
        throw new CategoryNotFoundException("Category does not exist");
    }


    @Transactional
    @Override
    public void deleteProductCategory(String categoryName) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        if (category != null) {
            productRepository.deleteByCategory(category);
            productCategoryRepository.delete(category);
            log.info("Admin delete category [{}]", categoryName);
        } else {
            log.warn("Category [{}] does not exist", categoryName);
            throw new CategoryNotFoundException("Category does not exist");
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }
}
