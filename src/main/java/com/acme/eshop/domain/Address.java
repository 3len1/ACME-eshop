package com.acme.eshop.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table()
public class Address extends PersistableEntity{

    private Integer postalCode;
    private String town;
    private String street;
    private User user;

    public Address() {
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}