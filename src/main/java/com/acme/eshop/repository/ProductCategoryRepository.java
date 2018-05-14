package com.acme.eshop.repository;

import com.acme.eshop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    void deleteByName(String name);
    void delete(ProductCategory category);
    ProductCategory save(ProductCategory category);
}
