package hello;

import org.junit.Test;

import java.util.Arrays;

public class TestVarArgs {
    @Test
    public void test1() {
        foo(new String[]{"aaa", "bbb", "ccc"});
        foo("ddd", "eee", "fff");
        foo(new int[]{111, 222, 333});
//        foo(111, 222, 333); // compile error
    }

    public void foo(String... args) {
        System.out.println(Arrays.toString(args));
    }

    public void foo(int[] args) {
        System.out.println(Arrays.toString(args));
    }
}
