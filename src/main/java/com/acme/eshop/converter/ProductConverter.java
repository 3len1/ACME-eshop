package com.acme.eshop.converter;

import com.acme.eshop.domain.Product;
import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.dto.ErrorMessage;
import com.acme.eshop.dto.ProductDto;
import javaslang.control.Try;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Eleni on 15/5/2018.
 */
@Component
public class ProductConverter {

    public Product getProductFromJson(ProductDto productDto, ProductCategory category){
        return Try.of(() -> {
            Product product = new Product();
            product.setCreatedDate(Instant.now().toEpochMilli());
            product.setProductCode(productDto.getProductCode());
            product.setDescription(productDto.getDescription());
            product.setImgUrl(productDto.getImgUrl());
            product.setPrice(new BigDecimal(productDto.getPrice()));
            product.setStockAmount(Integer.parseInt(productDto.getStock()));
            product.setPurchased(Integer.parseInt(productDto.getPurchased()));
            product.setCategory(category);
            return product;
        }).getOrElseGet(null);

    }

}
