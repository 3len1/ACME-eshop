package com.acme.eshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name = "ADDRESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Address extends PersistableEntity {

    @Column(name = "POSTAL_CODE")
    @Size(max = 5)
    private String postalCode;

    @Column(name = "TOWN", length = 20)
    private String town;

    @Column(name = "STREET", length = 60)
    private String street;

    @Column(name = "USER_ID")
    private Long userId;

    public Address(@Size(max = 5) String postalCode, String town, String street, Long userId) {
        this.postalCode = postalCode;
        this.town = town;
        this.street = street;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}