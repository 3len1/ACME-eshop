package com.acme.eshop.domain;


import com.acme.eshop.enums.Gender;
import com.acme.eshop.utils.DateConverter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eleni on 5/7/2018.
 */
@Entity
@Table(name = "USERS")
public class User extends PersistableEntity {


    @Column(name = "EMAIL", nullable = false, length = 30, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    @Column(name = "TOKEN", length = 16, unique = true)
    private UUID token;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "PHONE", nullable = false)
    @Size(max = 10)
    private String phone;

    @Column(name = "BIRTHDAY", columnDefinition = "TIMESTAMP")
    @Convert(converter = DateConverter.class)
    private Long birthday;

    @Column(name = "IS_ADMIN")
    private boolean isAdmin;


    @OneToOne(mappedBy = "ID", targetEntity = Address.class, fetch = FetchType.LAZY,
              cascade = CascadeType.ALL, orphanRemoval = true)
    private UUID addressId;


    @OneToMany(mappedBy = "ID", targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Order> orders;

    public User(String email, String password, UUID token, String lastName, String firstName, Gender gender,
                @Size(max = 10) String phone, Long birthday, boolean isAdmin, UUID addressId, List<Order> orders) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.phone = phone;
        this.birthday = birthday;
        this.isAdmin = isAdmin;
        this.addressId = addressId;
        this.orders = orders;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
