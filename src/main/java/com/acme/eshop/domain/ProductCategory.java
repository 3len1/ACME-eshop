package com.acme.eshop.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name = "PRODUCT_CATEGORIES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductCategory extends PersistableEntity {

    @Column(name = "CATEGORY_NAME", unique = true, nullable = false)
    String name;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        ProductCategory category = (ProductCategory) o;

        if (getName() != null ? !getName().equals(category.getName()) : category.getName() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
