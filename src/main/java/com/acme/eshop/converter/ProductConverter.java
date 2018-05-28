package com.acme.eshop.converter;

import com.acme.eshop.domain.Product;
import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.resources.ProductResource;
import javaslang.control.Try;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Eleni on 15/5/2018.
 */
@Component
public class ProductConverter {

    public Product getProduct(ProductResource productResource, ProductCategory category) {
        return Try.of(() -> {
            Product product = new Product();
            product.setProductCode(productResource.getProductCode());
            product.setDescription(productResource.getDescription());
            product.setImgUrl(productResource.getImgUrl());
            product.setPrice(new BigDecimal(productResource.getPrice() / 100));
            product.setStockAmount(Integer.parseInt(productResource.getStock()));
            if (product.getStockAmount() < 1) product.setStockAmount(1);
            product.setPurchased(0);
            product.setCategory(category);
            return product;
        }).get();
    }

}
