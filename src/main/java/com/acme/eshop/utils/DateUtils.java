package com.acme.eshop.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Eleni on 9/5/2018.
 */
public class DateUtils {

    public static Long epochNow() {
        return Instant.now().toEpochMilli();
    }
}
