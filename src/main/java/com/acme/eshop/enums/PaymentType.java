package com.acme.eshop.enums;

/**
 * Created by Eleni on 5/8/2018.
 */
public enum PaymentType {

    MASTERCARD,
    VISA,
    PAYPAL,
    CASH;

    public static PaymentType fromString(String paymentMethod) {
        if (paymentMethod == null)
            return null;

        paymentMethod = paymentMethod.toUpperCase();
        switch (paymentMethod) {
            case "MASTERCARD":
                return MASTERCARD;
            case "VISA":
                return VISA;
            case "PAYPAL":
                return PAYPAL;
            case "CASH":
                return CASH;
            default:
                throw new RuntimeException("UNKNOWN payment method " + paymentMethod);
        }
    }
}
