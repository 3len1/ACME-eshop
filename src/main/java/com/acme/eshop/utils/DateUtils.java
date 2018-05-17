package com.acme.eshop.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Eleni on 9/5/2018.
 */
public class DateUtils {

    public static Instant localDateTimeToInstant(LocalDateTime currentTime, ZoneId offset) {
        if (currentTime == null)
            return null;
        return currentTime.atZone(offset).toInstant();
    }

    public static Long localDateTimeToEpochUTC(LocalDateTime date, ZoneId offset) {
        if (date == null)
            return null;
        return localDateTimeToInstant(date, offset).toEpochMilli();
    }

    public static LocalDateTime instantToLocalDateTime(Instant date, ZoneId zone) {
        return LocalDateTime.ofInstant(date, zone);
    }

    public static LocalDateTime epochToLocalDateTime(Long date, ZoneId zone) {

        if (date == null) return null;

        return instantToLocalDateTime(Instant.ofEpochMilli(date), zone);
    }

    public static Long epochNow() {
        return Instant.now().toEpochMilli();
    }
}
