package com.acme.eshop.controller;

import com.acme.eshop.domain.Product;
import com.acme.eshop.resources.ProductResource;
import com.acme.eshop.service.LoginService;
import com.acme.eshop.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.timestamp.HttpTimestamper;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Api(description = "Product Controller", tags = "Products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private LoginService loginService;

    @ApiOperation("Get all products")
    @GetMapping(value = "/products")
    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll(pageable));
    }

    @ApiOperation("Get single product")
    @GetMapping(value = "/products/{productCode}")
    public ResponseEntity<Product> getProduct(@PathVariable String productCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.showProduct(productCode));
    }

    @ApiOperation("Get products by category")
    @GetMapping(value = "/{categoryName}/products")
    public ResponseEntity<Page<Product>> getProductByCategory(@PathVariable String categoryName,
                                                              Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.productByCategory(categoryName, pageable));
    }

    @ApiOperation("Get most purchased products by category")
    @GetMapping(value = "/{categoryName}/products/top")
    public ResponseEntity<Page<Product>> getMostPurchasedByCategory(@RequestHeader("sessionID") UUID sessionID,
                                                                    @PathVariable String categoryName,
                                                                    Pageable pageable) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(!loginService.getUser(sessionID).isAdmin()) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.mostPurchasedByCategory(categoryName, pageable));
    }

    @ApiOperation("Create a product")
    @PostMapping(value = "/products/")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductResource product,
                                 @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(!loginService.getUser(sessionID).isAdmin()) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @ApiOperation("Update product")
    @PutMapping(value = "/products/{productCode}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductResource product,
                                                 @RequestParam String productCode,
                                                 @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(!loginService.getUser(sessionID).isAdmin())
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(!productCode.equals(product.getProductCode()))
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(product));

    }

    @ApiOperation("Delete product")
    @DeleteMapping(value = "/products/{productCode}")
    public ResponseEntity deleteProduct(@RequestParam String productCode,
                                        @RequestHeader("sessionID") UUID sessionID) {

        if(loginService.getUser(sessionID) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(!loginService.getUser(sessionID).isAdmin()) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        productService.deleteProduct(productCode);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
