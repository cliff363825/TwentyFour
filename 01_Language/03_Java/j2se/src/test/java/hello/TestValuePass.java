package hello;

import org.junit.Test;

import java.util.Arrays;

public class TestValuePass {
    @Test
    public void testValuePass() {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = new int[]{1, 2, 3, 4, 5};
        MyData my = new MyData();

        change(i, str, num, arr, my);

        System.out.println("i=" + i); // 1
        System.out.println("str=" + str); // hello
        System.out.println("num=" + num); // 200
        System.out.println("arr=" + Arrays.toString(arr)); // [2, 2, 3, 4, 5]
        System.out.println("my.a=" + my.a); // 11

        // 方法的参数传递机制
        // 1. 形参是基本数据类型
        //  传递数据值
        // 2. 实参是引用数据类型
        //  传递地址值
        //  特殊的类型：String、包装类等对象不可变性
    }

    public static void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }
}
