package com.acme.eshop.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
public class Product extends PersistableEntity {

    private String productCode;
    private BigDecimal price;
    private String imgUrl;
    private String description;
    private int stockAmmount;
    private int sold;
    private ProductCategory category;

    private List<Item> items;

    public Product() {
    }

    public Product(String productCode, BigDecimal price, String imgUrl, String description, int stockAmmount, int sold, ProductCategory category) {
        this.productCode = productCode;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.stockAmmount = stockAmmount;
        this.sold = sold;
        this.category = category;
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

    public int getStockAmmount() {
        return stockAmmount;
    }

    public void setStockAmmount(int stockAmmount) {
        this.stockAmmount = stockAmmount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
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
}
