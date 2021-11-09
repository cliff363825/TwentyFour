package com.onevgo.j2se.i18n;

import lombok.extern.slf4j.Slf4j;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class I18NMain {
    public static void main(String[] args) {
//        testLocale();
//        testDateFormat();
//        testSimpleDateFormat();
//        testNumberFormat();
//        testMessageFormat();
        testResourceBundle();
    }

    private static void testLocale() {
        Locale locale = Locale.CHINA;
        log.info("country={} {}, language={}", locale.getCountry(), locale.getDisplayCountry(), locale.getLanguage());

        locale = new Locale("en", "US");
        log.info("country={} {}, language={}", locale.getCountry(), locale.getDisplayCountry(), locale.getLanguage());
    }

    private static void testDateFormat() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
        log.info("{} {}", date, dateFormat.format(date));
    }

    private static void testSimpleDateFormat() {
        String s = "1990-12-12 12:12:12";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            log.error("error=", e);
        }
        log.info("{}", date);
    }

    private static void testNumberFormat() {
        double d = 123456789.123D;

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.CHINA);
        log.info("{}", numberFormat.format(d));

        NumberFormat numberFormat1 = NumberFormat.getNumberInstance(Locale.FRANCE);
        log.info("{}", numberFormat1.format(d));

        NumberFormat numberFormat2 = NumberFormat.getCurrencyInstance(Locale.US);
        log.info("{}", numberFormat2.format(d));

        String s = "$123,456,789.12";
        try {
            Number number = numberFormat2.parse(s);
            log.info("{}", String.format("%.2f", number.doubleValue()));
        } catch (ParseException e) {
            log.error("error=", e);
        }
    }

    private static void testMessageFormat() {
        String msg = "Date: {0}, Salary: {1}";
        Locale locale = Locale.CHINA;

        Date date = new Date();
        Double sal = 12345.12d;

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        String dateStr = dateFormat.format(date);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String salStr = numberFormat.format(sal);

        String s = MessageFormat.format(msg, dateStr, salStr);
        log.info("{}", s);
    }

    private static void testResourceBundle() {
        Locale locale = Locale.CHINA;
        ResourceBundle bundle = ResourceBundle.getBundle("messages.i18n", locale);

        String dateLabel = bundle.getString("date");
        String salaryLabel = bundle.getString("salary");

        String msg = "{0}:{1}, {2}:{3}";

        Date date = new Date();
        Double sal = 12345.12d;

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        String dateStr = dateFormat.format(date);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String salStr = numberFormat.format(sal);

        String s = MessageFormat.format(msg, dateLabel, dateStr, salaryLabel, salStr);
        log.info("{}", s);
    }
}
