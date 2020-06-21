package hello;

import org.junit.Test;

public class TestLambda {
    @Test
    public void test1() {
        String s1 = myFunc((String s) -> {
            return s.toUpperCase();
        }, "abc");

        System.out.println("s1 = " + s1);
    }

    private String myFunc(MyFunc<String> func, String str) {
        return func.getValue(str);
    }
}
