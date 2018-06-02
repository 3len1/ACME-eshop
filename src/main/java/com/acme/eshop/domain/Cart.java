package com.acme.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Eleni on 14/5/2018.
 */
@Entity
@Table(name = "CART")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cart extends PersistableEntity implements Serializable {

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    @OneToMany(mappedBy="cart")
    private Set<CartItem> items;

    public Cart(User user, Set<CartItem> items) {
        this.user = user;
        this.items = items;
    }

    public Cart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }
}
