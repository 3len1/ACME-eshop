package com.acme.eshop.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name = "ITEMS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PersistableEntity {

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AMOUNT")
    private Integer amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private Order order;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID", referencedColumnName = "ID")
    private Cart cart;

    public Item(BigDecimal price, Integer amount, Product product, Order order, Cart cart) {
        this.price = price;
        this.amount = amount;
        this.product = product;
        this.order = order;
        this.cart = cart;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
