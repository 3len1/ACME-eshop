package com.acme.eshop.repository;

import com.acme.eshop.domain.Product;
import com.acme.eshop.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    void delete(Product product);
    void deleteByCategory(ProductCategory category);
    Product findByProductCode(String productCode);
    Page<Product> findByCategory(ProductCategory category, Pageable pageable);
    List<Product> findTop10ByOrderByPurchasedDesc();
    Page<Product> findByCategoryOrderByPurchasedDesc(ProductCategory category, Pageable pageable);
    List<Product> findByStockAmount(Integer stockAmount);

    @Query("select p from Product p where (p.category = :category or :category is null) and (p.price >= :priceMin or :priceMin is null) and (p.price <= :priceMax or :priceMax is null) and " +
            "(p.purchased >= :purchasedMin or :purchasedMin is null) and (p.purchased <= :purchasedMax or :purchasedMax is null)")
    Page<Product> findWithCriteria(@Param("category") ProductCategory category, @Param("priceMin") BigDecimal priceMin, @Param("priceMax") BigDecimal priceMax, @Param("purchasedMin") Integer purchasedMin, @Param("purchasedMax") Integer purchasedMax, Pageable pageable);
}
