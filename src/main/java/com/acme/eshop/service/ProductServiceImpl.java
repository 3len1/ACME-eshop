package com.acme.eshop.service;

import com.acme.eshop.converter.ProductConverter;
import com.acme.eshop.domain.Product;
import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.dto.ProductDto;
import com.acme.eshop.repository.ProductCategoryRepository;
import com.acme.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Component("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    private final String OUT_OF_STOCK="0";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductConverter productConverter;

    @Override
    public Product showProduct(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    @Override
    public Product createProduct(ProductDto product) {
        ProductCategory category = productCategoryRepository.findByName(product.getCategoryName());
        Product retrieveProduct = null;
        if (category != null)
            retrieveProduct = productConverter.getProductFromJson(product, category);
        return retrieveProduct!=null?productRepository.save(retrieveProduct): null;
    }

    @Override
    public Product updateProduct(ProductDto product) {
        ProductCategory category = productCategoryRepository.findByName(product.getCategoryName());
        Product retrieveProduct = null;
        if (category != null)
            retrieveProduct = productConverter.getProductFromJson(product, category);
        if (retrieveProduct!=null) retrieveProduct = productRepository.findByProductCode(product.getProductCode());
        return (retrieveProduct!=null)?productRepository.save(retrieveProduct): null;
    }

    @Override
    public void deleteProduct(ProductDto product) {
        ProductCategory category = productCategoryRepository.findByName(product.getCategoryName());
        Product retrieveProduct = null;
        if (category != null)
            retrieveProduct = productConverter.getProductFromJson(product, category);
        if (retrieveProduct!=null) retrieveProduct = productRepository.findByProductCode(product.getProductCode());
        if (retrieveProduct!=null) productRepository.delete(retrieveProduct);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> mostPurchasedByCategory(String categoryName, Pageable pageable) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        return category!=null? productRepository.findByCategoryOrderByPurchasedDesc(category, pageable):null;
    }
    @Override
    public Page<Product> productByCategory(String categoryName, Pageable pageable) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        return category!=null? productRepository.findByCategory(category, pageable):null;
    }

    @Override
    public Page<Product> search(String categoryName, BigDecimal priceMin, BigDecimal priceMax, Integer purchasedMin, Integer purchasedMax, Pageable pageable) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        return category!=null? productRepository.findWithCriteria(category, priceMin, priceMax, purchasedMin, purchasedMax, pageable): null;
    }

    @Override
    public List<Product> outOfStock() {
        return productRepository.findByStockAmount(Integer.parseInt(OUT_OF_STOCK));
    }

    @Override
    public List<Product> mostPurchased() {
        return productRepository.findTop10ByOrderByPurchasedDesc();
    }
}
