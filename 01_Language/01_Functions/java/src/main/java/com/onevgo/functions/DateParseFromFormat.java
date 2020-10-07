package com.onevgo.functions;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateParseFromFormat {
    public static void main(String[] args) throws ParseException {
        Date dateTime = DateUtils.parseDate("15-Feb-2009", Locale.ENGLISH, "dd-MMM-yyyy");
        System.out.println(dateTime);

        //jdk8+
        System.out.println(LocalDate.parse("15-Feb-2009", DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH)));
    }
}
