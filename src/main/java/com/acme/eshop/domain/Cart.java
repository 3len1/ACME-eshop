package com.acme.eshop.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Eleni on 14/5/2018.
 */
@Entity
@Table(name = "CART")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cart extends PersistableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "id", targetEntity = Item.class, fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Item> items;

    public Cart(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
