package date;

import org.junit.Test;

import java.time.*;
import java.util.Date;

public class TestInstant {
    @Test
    public void testNow() {
        Instant now = Instant.now();
        System.out.println("now = " + now); // now = 2019-12-22T09:36:02.520Z
    }

    @Test
    public void testInstant() {
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println("instant = " + instant);

        Instant instant1 = Instant.ofEpochSecond(System.currentTimeMillis() / 1000);
        System.out.println("instant1 = " + instant1);
    }

    @Test
    public void testInstantToLocalDateTime() {
        // Instant -> LocalDateTime
        Instant now = Instant.now();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        System.out.println("localDateTime = " + localDateTime); // localDateTime = 2019-12-22T17:56:48.575

//        LocalDateTime localDateTime1 = LocalDateTime.from(now); // java.time.DateTimeException: Unable to obtain LocalDateTime from TemporalAccessor
        LocalDateTime localDateTime1 = LocalDateTime.from(now.atOffset(ZoneOffset.of("+8")));
        System.out.println("localDateTime1 = " + localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.from(now.atZone(ZoneId.systemDefault()));
        System.out.println("localDateTime2 = " + localDateTime2);
    }

    @Test
    public void testInstantToDate() {
        // Instant -> Date
        Instant now = Instant.now();
        Date date = Date.from(now);
        System.out.println("date = " + date); // date = Sun Dec 22 17:57:49 CST 2019
    }
}
