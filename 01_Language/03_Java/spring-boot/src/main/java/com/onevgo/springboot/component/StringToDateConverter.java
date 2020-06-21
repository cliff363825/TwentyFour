package com.onevgo.springboot.component;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import java.util.*;

public class StringToDateConverter implements Converter<String, Date> {

    private static List<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();

    static {
        dateTimeFormatters.add(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        dateTimeFormatters.add(DateTimeFormat.forPattern("yyyy-MM-dd"));
    }

    @Override
    public Date convert(String source) {
        if (source == null) {
            return null;
        }

        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                DateTime dateTime = dateTimeFormatter.parseDateTime(source);
                return dateTime.toDate();
            } catch (Exception e) {
                // ignore
            }
        }

        return null;
    }
}
