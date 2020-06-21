package date;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestDuration {
    @Test
    public void test1() {
        // Duration：用于计算两个"时间"间隔，以秒和纳秒为基准
        LocalTime now = LocalTime.now();
        LocalTime localTime = LocalTime.of(15, 23, 32);

        // between()：静态方法，返回Duration对象，表示两个时间的间隔
        Duration duration = Duration.between(localTime, now);
        System.out.println("duration = " + duration);

        long seconds = duration.getSeconds();
        System.out.println("seconds = " + seconds);
    }

    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 8, 8, 8, 8);
        LocalDateTime localDateTime1 = LocalDateTime.of(2018, 8, 8, 8, 8, 8);
        Duration duration = Duration.between(localDateTime, localDateTime1);
        System.out.println("duration = " + duration);

        long days = duration.toDays();
        System.out.println("days = " + days);
    }
}
