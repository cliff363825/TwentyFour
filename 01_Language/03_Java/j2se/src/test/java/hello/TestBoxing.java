package hello;

import org.junit.Test;

public class TestBoxing {
    @Test
    public void testBoxing() {
        // 装箱：基本数据类型 -> 包装类
        // int -> Integer
        int i = 996;
        Integer i1 = new Integer(i);
        Integer i2 = Integer.valueOf(996);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
    }

    @Test
    public void testInteger1() {
        Integer i1 = 1;
        Integer i2 = 1;
        System.out.println("i1 = i2?" + (i1 == i2)); // true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println("i3 = i4?" + (i3 == i4)); // false
        // Integer.valueOf(int) 对值为 -128 ～ 127 的Integer对象做了缓存
    }

    @Test
    public void testUnboxing() {
        // 拆箱：包装类 -> 基本数据类型
        // Integer -> int
        Integer i1 = new Integer(996);
        int i2 = i1.intValue();
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
    }

    @Test
    public void testString() {
        // 基本数据类型 -> String
        // int -> String
        int i = 996;
        String s = String.valueOf(i);
        String s1 = Integer.toString(i);
        System.out.println("s = " + s);
        System.out.println("s1 = " + s1);

        // 包装类 -> String
        // Integer -> String
        Integer i1 = new Integer(i);
        String s2 = i1.toString();
        System.out.println("s2 = " + s2);
    }

    @Test
    public void testString1() {
        // String -> 基本数据类型
        // String -> int
        String s = "996";
        int i1 = Integer.parseInt(s);
        int i2 = new Integer(s).intValue();
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
    }
}
