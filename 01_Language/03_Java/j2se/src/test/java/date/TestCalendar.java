package date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class TestCalendar {
    @Test
    public void test1() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, 8);
        System.out.println("当前时间日设置为8后，时间是：" + calendar.getTime());

        calendar.add(Calendar.HOUR, 2);
        System.out.println("当前时间加2小时后，时间是：" + calendar.getTime());

        calendar.add(Calendar.MONTH, -2);
        System.out.println("当前日期减2个月后，时间是：" + calendar.getTime());
    }

    @Test
    public void test2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.JANUARY, 31);
        // calendar.getTime()=2019-01-31 10:50:39
        System.out.printf("calendar.getTime()=%tF %<tT\n", calendar.getTime());

        calendar.add(Calendar.MONTH, 1);
        // calendar.getTime()=2019-02-28 10:50:39
        System.out.printf("calendar.getTime()=%tF %<tT\n", calendar.getTime());
    }
}
