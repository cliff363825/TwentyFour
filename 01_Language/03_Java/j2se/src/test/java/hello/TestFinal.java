package hello;

import org.junit.Test;

public class TestFinal {
    // 1. 声明并赋值
    public final double PI = 3.14;

    // 2. 代码块中赋值
    public final double PI1;

    {
        PI1 = 3.141;
    }

    // 3. 构造器中赋值
    public final double PI2;

    public TestFinal() {
        PI2 = 3.1415;
    }

    @Test
    public void test1() {
        System.out.println(PI);
        System.out.println(PI1);
        System.out.println(PI2);
    }
}
