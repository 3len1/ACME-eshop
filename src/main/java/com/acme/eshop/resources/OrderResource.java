package com.acme.eshop.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * Created by Eleni on 17/5/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResource {

    @NotNull(message = "{order.code.null}")
    private String orderCode;
    @NotNull(message = "{order.payment.null}")
    private String paymentMethod;
    private String comments;
}
