package com.acme.eshop.repository;

import com.acme.eshop.domain.Product;
import com.acme.eshop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductCode(String productCode);

    Product findByCategory(ProductCategory category);

    Product save(Product product);

    List<Product> findTop10OrOrderBySold();

    List<Product> findByStockAmount(Integer stockAmount);
}
