package com.acme.eshop.controller;

import com.acme.eshop.domain.ProductCategory;
import com.acme.eshop.exceptions.WrongCredentialsException;
import com.acme.eshop.service.LoginService;
import com.acme.eshop.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 5/27/2018.
 */
@RestController
@Api(description = "Product category  Controller", tags = "Product categories")
public class ProductCategoryController {

    @Autowired
    LoginService loginService;
    @Autowired
    ProductCategoryService productCategoryService;

    @ApiOperation("Get all categories")
    @GetMapping(value = "/categories")
    public ResponseEntity<List<ProductCategory>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productCategoryService.getAll());
    }

    @ApiOperation("Create product category")
    @PostMapping(value = "/admin/categories")
    public ResponseEntity<ProductCategory> createCategory(@NotNull @RequestBody String name,
                                                 @RequestHeader("sessionID") UUID sessionID) {
        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can create categories");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productCategoryService.createProductCategory(name));
    }

    @ApiOperation("Update product category")
    @PutMapping(value = "/admin/categories")
    public ResponseEntity<ProductCategory> updateCategory(@RequestParam String categoryName,
                                                 @RequestHeader("sessionID") UUID sessionID) {
        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can update categories");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productCategoryService.updateProductCategory(categoryName));
    }

    @ApiOperation("Delete product category")
    @DeleteMapping(value = "/admin/categories")
    public ResponseEntity deleteProduct(@RequestParam String categoryName,
                                        @RequestHeader("sessionID") UUID sessionID) {

        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can delete categories");

        productCategoryService.deleteProductCategory(categoryName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
