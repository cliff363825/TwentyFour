package com.onevgo.functions;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateCreateFromFormat {
    public static void main(String[] args) {
        System.out.println(Locale.getDefault());
        try {
            Date date = DateUtils.parseDate("15-Feb-2009", Locale.ENGLISH, "d-MMM-yyyy");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //jdk8+
        LocalDate localDate = LocalDate.parse("15-Feb-2009", DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.ENGLISH));
        System.out.println(localDate);
    }
}
