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

        PaymentType paymentType;
        paymentMethod = paymentMethod.toUpperCase();
        switch (paymentMethod) {
            case "MASTERCARD":
                paymentType = MASTERCARD;
                break;
            case "VISA":
                paymentType = VISA;
                break;
            case "PAYPAL":
                paymentType = PAYPAL;
                break;
            case "CASH":
                paymentType = CASH;
                break;
            default:
                throw new RuntimeException("UNKNOWN payment method " + paymentMethod);
        }
        return paymentType;
    }
}
