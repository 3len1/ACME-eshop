package com.acme.eshop.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
public class Item extends PersistableEntity {

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AMOUNT")
    private Integer amount;


    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID", referencedColumnName="ID", nullable = false)
    private Product product;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID",referencedColumnName="ID")
    private List<Order> order;

    public Item(BigDecimal price, Integer amount, Product product, List<Order> order) {
        this.price = price;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
