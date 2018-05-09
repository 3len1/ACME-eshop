package com.acme.eshop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
public class ProductCategory extends PersistableEntity{

    @Column(name = "CATEGORY_NAME", unique = true, nullable = false)
    String name;

    @OneToMany(mappedBy = "id", targetEntity = Product.class, fetch = FetchType.LAZY,
               cascade = CascadeType.REMOVE, orphanRemoval=true)
    List<Product> product;

    public ProductCategory(String name, List<Product> product) {
        this.name = name;
        this.product = product;
    }

    public ProductCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        ProductCategory category = (ProductCategory) o;

        if (getName() != null ? !getName().equals( category.getName()) : category.getName() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
