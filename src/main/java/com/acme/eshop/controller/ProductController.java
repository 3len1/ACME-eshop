package com.acme.eshop.controller;

import com.acme.eshop.domain.Product;
import com.acme.eshop.exceptions.*;
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
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Api(description = "Product Controller", tags = "Products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private LoginService loginService;

    @ApiOperation("Get product")
    @GetMapping(value = "/products/{productCode}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productCode") String code) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.showProduct(code));
    }

    @ApiOperation("Get all products")
    @GetMapping(value = "/products")
    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll(pageable));
    }

    @ApiOperation("Product search")
    @GetMapping(value = "/products/search")
    public ResponseEntity<Page<Product>> search(@RequestParam(name = "category", required = false) String category,
                                                @RequestParam(name = "priceMin", required = false) Long priceMin,
                                                @RequestParam(name = "priceMax", required = false) Long priceMax,
                                                @RequestParam(name = "soldMin", required = false) Integer soldMin,
                                                @RequestParam(name = "soldMax", required = false) Integer soldMax,
                                                Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.search(category, priceMin, priceMax, soldMin, soldMax, pageable));
    }

    @ApiOperation("Get most popular products")
    @GetMapping(value = "/products/popular")
    public ResponseEntity<List<Product>> getMostPopularProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.mostPurchased());
    }

    @ApiOperation("Get products by category")
    @GetMapping(value = "/{categoryName}/products")
    public ResponseEntity<Page<Product>> getProductByCategory(@PathVariable(name = "categoryName") String category,
                                                              Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.productByCategory(category, pageable));
    }

    @ApiOperation("Get most popular products by category")
    @GetMapping(value = "/{categoryName}/products/popular")
    public ResponseEntity<Page<Product>> getMostPopularByCategory(@PathVariable(name = "categoryName") String category,
                                                                  Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.mostPurchasedByCategory(category, pageable));
    }

    @ApiOperation("Admin get out of stock products")
    @GetMapping(value = "/admin/products/stock")
    public ResponseEntity<List<Product>> getOutOfStockProducts(@RequestHeader("sessionID") UUID sessionID) {
        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can create products");
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.outOfStock());
    }

    @ApiOperation(value = "Admin create product", notes = "price is Long that means 1000 is 10 euro")
    @PostMapping(value = "/admin/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductResource product,
                                                 @RequestHeader("sessionID") UUID sessionID) {
        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can create products");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @ApiOperation(value = "Admin update product", notes = "price is Long that means 1000 is 10 euro")
    @PutMapping(value = "/admin/products/{productCode}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "productCode") String code,
                                                 @Valid @RequestBody ProductResource product,
                                                 @RequestHeader("sessionID") UUID sessionID) {

        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can update products");
        if (product.getProductCode() == null) product.setProductCode(code);
        else if (!product.getProductCode().equals(code))
            throw new ResourceNotValid("Product code must be the same with the product you want to update");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productService.updateProduct(product));
    }

    @ApiOperation("Admin delete product")
    @DeleteMapping(value = "/admin/products/{productCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productCode") String code,
                                                @RequestHeader("sessionID") UUID sessionID) {

        if (!loginService.getUser(sessionID).isAdmin())
            throw new WrongCredentialsException("Only admin can delete products");
        productService.deleteProduct(code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted");
    }

}
