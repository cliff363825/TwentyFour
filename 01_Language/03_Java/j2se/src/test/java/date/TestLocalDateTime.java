package date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestLocalDateTime {
    @Test
    public void testNow() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now); // now = 2019-12-22T17:13:37.802

        LocalDateTime now1 = LocalDateTime.now(ZoneId.of("GMT"));
        System.out.println("now1 = " + now1); // now1 = 2019-12-22T09:13:37.803

        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("now2 = " + now2); // now2 = 2019-12-22T17:13:37.803
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2008, 8, 8), LocalTime.of(8, 8, 8));
        System.out.println("localDateTime = " + localDateTime);

        int dayOfYear = localDateTime.getDayOfYear();
        int dayOfMonth = localDateTime.getDayOfMonth();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        int monthValue = localDateTime.getMonthValue();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        System.out.printf("%d-%d-%d %d:%d:%d 第%d天，星期%d\n", year, monthValue, dayOfMonth, hour, minute, second, dayOfYear, dayOfWeek.getValue());
    }

    @Test
    public void testLocalDateTime1() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.withMonth(1)
                .withDayOfMonth(31)
                .plusMonths(1);
        // localDateTime = 2019-02-28T17:33:45.168
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime localDateTime1 = now.withMonth(3)
                .withDayOfMonth(31)
                .minusMonths(1);
        // localDateTime1 = 2019-02-28T17:33:45.168
        System.out.println("localDateTime1 = " + localDateTime1);
    }

    @Test
    public void testDateTimeFormatter() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(now);
        System.out.println("format=" + format); // format=2019-12-09 17:52:30

        LocalDateTime parse = LocalDateTime.parse("2019-12-09 17:46:49", dateTimeFormatter);
        System.out.println("parse=" + parse); // parse=2019-12-09T17:46:49
    }

    @Test
    public void testLocalDateTimeToDate() {
        LocalDateTime now = LocalDateTime.now();

        // LocalDateTime -> Date
        Date date = Date.from(now.toInstant(ZoneOffset.of("+8")));
        System.out.println("date = " + date);

        Date date1 = Date.from(now.atOffset(ZoneOffset.of("+8")).toInstant());
        System.out.println("date1 = " + date1);

        Date date2 = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("date2 = " + date2);

        // LocalDate -> LocalDateTime -> Date
        LocalDate now1 = LocalDate.now();
        Date date3 = Date.from(now1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("date3 = " + date3);
    }
}
