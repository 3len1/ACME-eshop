package com.acme.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Eleni on 6/2/2018.
 */
@Entity
@Table(name = "CART_ITEMS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CartItem extends PersistableEntity implements Serializable {

   @Column(name = "AMOUNT")
    private Integer amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "CART_ID", referencedColumnName = "ID", nullable = true)
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Integer amount, Product product, Cart cart) {
        this.amount = amount;
        this.product = product;
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
