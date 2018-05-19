package com.acme.eshop.service;

import com.acme.eshop.converter.ProductConverter;
import com.acme.eshop.domain.Product;
import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.resources.ProductResource;
import com.acme.eshop.repository.ProductCategoryRepository;
import com.acme.eshop.repository.ProductRepository;
import com.acme.eshop.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component("productService")
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String OUT_OF_STOCK = "0";
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


    @Transactional
    @Override
    public Product createProduct(ProductResource product) {
        ProductCategory category = productCategoryRepository.findByName(product.getCategoryName());
        Product retrieveProduct = null, checkProduct;
        if (product.getProductCode() != null)
            checkProduct = productRepository.findByProductCode(product.getProductCode());
        else{
            log.warn("The given product doesn't have product code");
            return null;
        }
        if (category != null && checkProduct == null) {
            retrieveProduct = productConverter.getProduct(product, category);
            retrieveProduct.setCreatedDate(DateUtils.epochNow());
            log.info("Admin create a new product [{}]", product.getProductCode());
        }
        else{
            log.warn("Ether product [{}] already exist or category [{}] doesn't exist" , product.getProductCode(), product.getCategoryName());
            return null;
        }
        return retrieveProduct != null ? productRepository.save(retrieveProduct) : null;
    }


    @Transactional
    @Override
    public Product updateProduct(ProductResource product) {
        ProductCategory category = productCategoryRepository.findByName(product.getCategoryName());
        Product retrieveProduct = null, checkProduct;
        if (product.getProductCode() != null)
            checkProduct = productRepository.findByProductCode(product.getProductCode());
        else {
            log.warn("The given product doesn't have product code");
            return null;
        }
        if (category != null && checkProduct != null)
            retrieveProduct = productConverter.getProduct(product, category);
        else{
            log.warn("Ether product [{}] or category [{}] doesn't exist" , product.getProductCode(), product.getCategoryName());
            return null;
        }
        return (retrieveProduct != null) ? productRepository.save(retrieveProduct) : null;
    }


    @Transactional
    @Override
    public void deleteProduct(String productCode) {
        Optional.ofNullable(productRepository.findByProductCode(productCode)).ifPresent( product -> {
            productRepository.delete(product);
            log.info("Admin delete product [{}]", product.getProductCode());
        });
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> mostPurchasedByCategory(String categoryName, Pageable pageable) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        if (category == null)log.warn("Category [{}] does not exist", categoryName);
        return category != null ? productRepository.findByCategoryOrderByPurchasedDesc(category, pageable) : null;
    }

    @Override
    public Page<Product> productByCategory(String categoryName, Pageable pageable) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        if (category == null) log.warn("Category [{}] does not exist", categoryName);
        return category != null ? productRepository.findByCategory(category, pageable) : null;
    }

    @Override
    public Page<Product> search(String categoryName, BigDecimal priceMin, BigDecimal priceMax, Integer purchasedMin, Integer purchasedMax, Pageable pageable) {
        ProductCategory category = productCategoryRepository.findByName(categoryName);
        return category != null ? productRepository.findWithCriteria(category, priceMin, priceMax, purchasedMin, purchasedMax, pageable) : null;
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
