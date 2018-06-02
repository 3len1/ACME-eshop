package com.acme.eshop.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name = "ADDRESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Address extends PersistableEntity implements Serializable {

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "TOWN", length = 20)
    private String town;

    @Column(name = "STREET", length = 60)
    private String street;

    public Address(@Size(max = 5) String postalCode, String town, String street) {
        this.postalCode = postalCode;
        this.town = town;
        this.street = street;
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

}