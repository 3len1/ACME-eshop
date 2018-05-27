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

    public Cart(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
