package com.acme.eshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Eleni on 9/5/2018.
 */
@Configuration
@Validated
public class AcmeShopConfig {
    private String timeZone = "Europe/Athens";

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
