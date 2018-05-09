package com.acme.eshop.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
public class Product extends PersistableEntity {

    @Column(name = "ORDER_CODE", unique = true)
    private String productCode;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STOCK")
    private Integer stockAmmount;

    @Column(name = "SOLD")
    private Integer sold;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID",referencedColumnName="ID")
    private ProductCategory category;

    @OneToMany(mappedBy = "id", targetEntity = Item.class, fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Item> items;


    public Product(String productCode, BigDecimal price, String imgUrl, String description,
                   Integer stockAmmount, Integer sold, ProductCategory category, List<Item> items) {
        this.productCode = productCode;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.stockAmmount = stockAmmount;
        this.sold = sold;
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

    public Integer getStockAmmount() {
        return stockAmmount;
    }

    public void setStockAmmount(Integer stockAmmount) {
        this.stockAmmount = stockAmmount;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Product product = (Product) o;

        if (getProductCode() != null ? !getProductCode().equals(product.getProductCode()) :product.getProductCode() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getProductCode() != null ? getProductCode().hashCode() : 0;
    }
}
