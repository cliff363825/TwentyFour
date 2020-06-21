package hello;

import org.junit.Test;

public class TestStep {
    // 实现f(n)：求n步台阶，一共有几种走法
    public int f(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }

        if (n == 1 || n == 2) {
            return n;
        }

        return f(n - 1) + f(n - 2);
    }

    @Test
    public void testStep1() {
        System.out.println(f(4));
    }

    public int loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }

        if (n == 1 || n == 2) {
            return n;
        }

        // n=1 1  1
        // n=2 2  1+1 2
        // n=3 3  1+1+1 2+1 1+2
        // n=4 5  1+1+1+1 2+1+1 1+2+1 1+1+2 2+2
        // n=5 8  (1+1+1+1 2+1+1 1+2+1 1+1+2 2+2)+1 (1+1+1 2+1 1+2)+2

        int step1 = 1;
        int step2 = 2;
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum = step1 + step2;
            step1 = step2;
            step2 = sum;
        }

        return sum;
    }

    @Test
    public void testStep2() {
        System.out.println(loop(5));
    }
}
