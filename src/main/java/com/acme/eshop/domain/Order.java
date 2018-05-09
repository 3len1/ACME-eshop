package com.acme.eshop.domain;

import com.acme.eshop.enums.PaymentType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
public class Order extends PersistableEntity {

    private String orderCode;
    private PaymentType paymentMethod;
    private Long paymentDate;
    private BigDecimal totalPrice;
    private String comments;

    private User user;

    private List<Item> items;

    public Order(String orderCode, PaymentType paymentMethod, Long paymentDate, BigDecimal totalPrice, String comments, User user) {
        this.orderCode = orderCode;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
        this.comments = comments;
        this.user = user;
    }

    public Order() {
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public PaymentType getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentType paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
