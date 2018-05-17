package com.acme.eshop.utils;

import java.math.BigDecimal;

/**
 * Created by Eleni on 17/5/2018.
 */
public class PriceUtils {

    public static BigDecimal bigDecimalMultiply(BigDecimal price, Integer quantity){
        return BigDecimal.valueOf(price.doubleValue()*quantity);
    }
}
