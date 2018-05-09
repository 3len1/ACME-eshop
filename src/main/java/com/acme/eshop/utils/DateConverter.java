package com.acme.eshop.utils;

import javax.persistence.AttributeConverter;
import java.util.Date;

/**
 * Created by Eleni on 9/5/2018.
 */
public class DateConverter implements AttributeConverter<Long, Date> {

    @Override
    public Date convertToDatabaseColumn(Long longDate) {
        if (longDate == null) {
            return null;
        }
        return new Date(longDate);
    }

    @Override
    public Long convertToEntityAttribute(Date dbDate) {
        if (dbDate == null) {
            return null;
        }
        return dbDate.getTime();
    }
}
