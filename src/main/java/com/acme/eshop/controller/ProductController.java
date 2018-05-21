package com.acme.eshop.controller;

import com.acme.eshop.domain.Product;
import com.acme.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
        return ResponseEntity.ok()
                .body(productService.getAll(pageable));
    }

}
