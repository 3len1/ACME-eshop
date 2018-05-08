package com.acme.eshop.domain;

import java.math.BigDecimal;

/**
 * Created by Eleni on 5/8/2018.
 */
public class Item extends PersistableEntity {

    private BigDecimal price;
    private Integer ammount;
    private Product product;
    private Order order;

    public Item(BigDecimal price, Integer ammount, Product product, Order order) {
        this.price = price;
        this.ammount = ammount;
        this.product = product;
        this.order = order;
    }

    public Item() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
