package com.acme.eshop.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name = "PRODUCTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product extends PersistableEntity {

    @Column(name = "PRODUCT_CODE", unique = true)
    private String productCode;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STOCK")
    private Integer stockAmount;

    @Column(name = "PURCHASED")
    private Integer purchased;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private ProductCategory category;

    @OneToMany(mappedBy = "product", targetEntity = Item.class, fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Item> items;


    public Product(String productCode, BigDecimal price, String imgUrl, String description,
                   Integer stockAmmount, Integer purchased, ProductCategory category, List<Item> items) {
        this.productCode = productCode;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.stockAmount = stockAmmount;
        this.purchased = purchased;
        this.category = category;
        this.items = items;
    }

    public Product() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    public Integer getPurchased() {
        return purchased;
    }

    public void setPurchased(Integer purchased) {
        this.purchased = purchased;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean incriseStockAmount(Integer amount){
        if (this.stockAmount < 0 || amount < 0){
            return false;
        }
        this.stockAmount += amount;
        return true;
    }

    public boolean minusStockAmount(Integer amount){
        if (this.stockAmount - amount < 1){
          return false;
        }
        this.stockAmount -= amount;
        return true;
    }

    public boolean incisePurchased(Integer amount){
        if (this.purchased < 0 || amount < 0)
            return false;
        this.purchased += amount;
        return true;
    }

    public boolean minusPurchased(Integer amount){
        if (this.purchased - amount < 1)
            return false;
        this.purchased += amount;
        return true;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Product product = (Product) o;

        if (getProductCode() != null ? !getProductCode().equals(product.getProductCode()) : product.getProductCode() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getProductCode() != null ? getProductCode().hashCode() : 0;
    }
}
