package date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

public class TestPeriod {
    @Test
    public void test1() {
        // Period：用于计算两个"日期"间隔，以年、月、日衡量
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2008, 8, 8);
        Period period = Period.between(localDate, localDate1);
        System.out.println("period = " + period);

        int years = period.getYears();
        System.out.println("years = " + years);

        int months = period.getMonths();
        System.out.println("months = " + months);

        int days = period.getDays();
        System.out.println("days = " + days);

        Period period1 = period.withYears(2);
        System.out.println("period1 = " + period1);
    }
}
