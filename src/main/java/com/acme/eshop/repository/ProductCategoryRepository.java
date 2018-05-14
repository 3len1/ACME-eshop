package com.acme.eshop.repository;

import com.acme.eshop.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory save(ProductCategory category);
    void deleteByName(String name);
    void delete(ProductCategory category);
    
    Page<ProductCategory> findAll(Pageable pageable);
}
