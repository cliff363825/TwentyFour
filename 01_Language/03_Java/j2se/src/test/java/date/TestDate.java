package date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {
    @Test
    public void testDate() {
        Date date = new Date();
        System.out.println("date = " + date); // date = Sun Dec 22 13:04:37 CST 2019

        long time = date.getTime();
        System.out.println("time = " + time); // time = 1576991077387

        long currentTimeMillis = System.currentTimeMillis();
        Date date1 = new Date(currentTimeMillis);
        System.out.println("date1 = " + date1); // date1 = Sun Dec 22 13:04:37 CST 2019
        System.out.println("date1.getTime() == currentTimeMillis?" + (date1.getTime() == currentTimeMillis)); // date1.getTime() == currentTimeMillis?true

        Date date2 = new java.sql.Date(date.getTime());
        System.out.println("date2 = " + date2); // date2 = 2019-12-22
    }

    @Test
    public void testSimpleDateFormat() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println("format = " + format); // format = 2019-12-22 13:29:29

        try {
            Date parse = simpleDateFormat.parse("2008-08-08 08:08:08");
            System.out.println("parse = " + parse); // parse = Fri Aug 08 08:08:08 CST 2008
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeZone() {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        String format = simpleDateFormat.format(date);
        System.out.println("format = " + format); // format = 星期日, 22 十二月 2019 13:53:57 CST

        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-8")); // 西8时区
        String format1 = simpleDateFormat.format(date);
        System.out.println("format1 = " + format1); // format1 = 星期六, 21 十二月 2019 21:53:57 GMT-08:00
    }

    @Test
    public void testDateToInstant() {
        // Date -> Instant
        Date date = new Date();
        Instant instant = date.toInstant();
        System.out.println("instant = " + instant);
    }

    @Test
    public void testDateToLocalDateTime() {
        // Date -> LocalDateTime(LocalDate+LocalTime)
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime = " + localDateTime);
    }
}
