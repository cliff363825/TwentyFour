package date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class TestZoneId {
    @Test
    public void testZoneId() {
        // ZoneId 的 getAvailableZoneIds()：获取所有的ZoneId
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    @Test
    public void testZoneId2() {
        // ZoneId的of()：获取指定时区的时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("now = " + now);
    }

    @Test
    public void testZoneId3() {
        // ZonedDateTime的now()：获取本时区的ZonedDateTime对象
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("now = " + now); // now = 2019-12-22T18:24:24.799+08:00[Asia/Shanghai]

        // ZonedDateTime的now(ZoneId id)：获取指定时区的ZonedDateTime对象
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("now1 = " + now1); // now1 = 2019-12-22T19:25:24.458+09:00[Asia/Tokyo]
    }
}
