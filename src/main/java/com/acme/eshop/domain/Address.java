package com.acme.eshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends PersistableEntity{

    @Column(name = "POSTAL_CODE")
    @Size(max = 5)
    private String postalCode;

    @Column(name="TOWN", length = 20)
    private String town;

    @Column(name="STREET", length = 60)
    private String street;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Address(@Size(max = 5) String postalCode, String town, String street, User user) {
        this.postalCode = postalCode;
        this.town = town;
        this.street = street;
        this.user = user;
    }

    public Address() {
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
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