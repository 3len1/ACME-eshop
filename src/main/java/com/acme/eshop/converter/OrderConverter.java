package com.acme.eshop.converter;

import com.acme.eshop.domain.Order;
import com.acme.eshop.dto.OrderDto;
import com.acme.eshop.enums.PaymentType;
import javaslang.control.Try;
import org.springframework.stereotype.Component;

/**
 * Created by Eleni on 17/5/2018.
 */
@Component
public class OrderConverter {

    public Order getOrder(OrderDto orderDto) {
        return Try.of(() -> {
            Order order = new Order();
            order.setOrderCode(orderDto.getOrderCode());
            order.setComments(orderDto.getComments());
            order.setPaymentMethod(PaymentType.fromString(orderDto.getPaymentMethod()));
            return order;
        }).getOrElseGet(null);
    }
}
