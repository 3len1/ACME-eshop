package com.acme.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Eleni on 6/2/2018.
 */
@Entity
@Table(name = "ORDER_ITEMS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderItem extends PersistableEntity implements Serializable {

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AMOUNT")
    private Integer amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = true)
    private Order order;

    public OrderItem() {
    }

    public OrderItem(BigDecimal price, Integer amount, Product product, Order order) {
        this.price = price;
        this.amount = amount;
        this.product = product;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
