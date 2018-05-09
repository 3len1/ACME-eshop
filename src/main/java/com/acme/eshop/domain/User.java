package com.acme.eshop.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 5/7/2018.
 */
@Entity
@Table()
public class User extends PersistableEntity {

    private String email;
    private String password;
    private UUID token;
    private String lastName;
    private String firstName;
    private String phone;
    private UUID addressId;
    private boolean isAdmin;
    private List<Order> orders;


    public User(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public User() {
        super();
    }

    @Column(name = "EMAIL", nullable = false, length = 30, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "TOKEN", length = 16)
    @GeneratedValue(generator = "system-uuid")
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Column(name = "LAST_NAME", length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "FIRST_NAME", length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "PHONE", nullable = false, length = 10)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "IS_ADMIN")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Column(name = "ADDRESS_ID", length = 30)
    @OneToOne(mappedBy = "user", targetEntity = Address.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    @OneToMany(mappedBy = "user", targetEntity = Order.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
