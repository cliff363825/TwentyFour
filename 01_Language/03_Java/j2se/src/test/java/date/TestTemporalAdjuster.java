package date;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TestTemporalAdjuster {
    @Test
    public void test1() {
        // TemporalAdjuster：时间校正器
        // 获取当前日期的下一个周日是哪天？
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        LocalDateTime localDateTime = LocalDateTime.now().with(temporalAdjuster);
        System.out.println("localDateTime = " + localDateTime); // localDateTime = 2019-12-29T18:45:42.397

        // 获取下一个工作日是哪天？
        LocalDate localDate = LocalDate.now().with(temporal -> {
            LocalDate localDate1 = (LocalDate) temporal;
            switch (localDate1.getDayOfWeek()) {
                case FRIDAY:
                    return localDate1.plusDays(3);
                case SATURDAY:
                    return localDate1.plusDays(2);
                default:
                    return localDate1.plusDays(1);
            }
        });
        System.out.println("下一个工作日是 = " + localDate);
    }
}
