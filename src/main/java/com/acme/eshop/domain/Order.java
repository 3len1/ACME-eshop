package com.acme.eshop.domain;

import com.acme.eshop.enums.PaymentType;
import com.acme.eshop.utils.DateConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Eleni on 5/8/2018.
 */
@Entity
@Table(name="ORDERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Order extends PersistableEntity implements Serializable {

    @Column(name = "ORDER_CODE", unique = true)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_METH")
    private PaymentType paymentMethod;

    @Column(name = "PAYMENT_DATE", columnDefinition = "TIMESTAMP")
    @Convert(converter = DateConverter.class)
    private Long paymentDate;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CANCELED")
    private boolean canceled;


    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID",referencedColumnName="ID")
    private User user;

    @OneToMany(mappedBy = "id", targetEntity = Item.class, fetch = FetchType.LAZY,
               cascade = CascadeType.REMOVE, orphanRemoval=true)
    private List<Item> items;

    public Order(String orderCode, PaymentType paymentMethod, Long paymentDate, BigDecimal totalPrice,
                 String comments,boolean canceled, User user, List<Item> items) {
        this.orderCode = orderCode;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
        this.comments = comments;
        this.canceled =canceled;
        this.user = user;
        this.items = items;
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

    public boolean isCanceled() { return canceled; }

    public void setCanceled(boolean canceled) { this.canceled = canceled;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Order order = (Order) o;

        if (getOrderCode() != null ? !getOrderCode().equals(order.getOrderCode()) : order.getOrderCode() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getOrderCode() != null ? getOrderCode().hashCode() : 0;
    }
}
