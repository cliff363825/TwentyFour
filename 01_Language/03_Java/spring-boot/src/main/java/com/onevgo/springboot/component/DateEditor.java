package com.onevgo.springboot.component;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateEditor extends PropertyEditorSupport {
    private static List<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();

    static {
        dateTimeFormatters.add(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        dateTimeFormatters.add(DateTimeFormat.forPattern("yyyy-MM-dd"));
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Date date = null;
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                DateTime dateTime = dateTimeFormatter.parseDateTime(text);
                date = dateTime.toDate();
                break;
            } catch (Exception e) {
                // ignore
            }
        }

        setValue(date);
    }
}
