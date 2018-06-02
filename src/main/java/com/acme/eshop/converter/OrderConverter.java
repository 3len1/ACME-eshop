package com.acme.eshop.converter;

import com.acme.eshop.domain.Order;
import com.acme.eshop.exceptions.ResourceNotValid;
import com.acme.eshop.resources.OrderResource;
import com.acme.eshop.enums.PaymentType;
import javaslang.control.Try;
import org.springframework.stereotype.Component;

/**
 * Created by Eleni on 17/5/2018.
 */
@Component
public class OrderConverter {

    public Order getOrder(OrderResource orderResource) {
        return Try.of(() -> {
            Order order = new Order();
            order.setOrderCode(orderResource.getOrderCode());
            order.setComments(orderResource.getComments());
            order.setPaymentMethod(PaymentType.fromString(orderResource.getPaymentMethod()));
            return order;
        }).getOrElseThrow(()-> new ResourceNotValid("Order resource is not valid"));
    }
}
