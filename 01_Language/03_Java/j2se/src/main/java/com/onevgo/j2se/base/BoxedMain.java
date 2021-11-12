package com.onevgo.j2se.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoxedMain {
    public static void main(String[] args) {
//        testBoxed();
//        testInteger();
//        testUnboxed();
        testString();
        testString1();
    }

    private static void testBoxed() {
        // 装箱：基本数据类型 -> 包装类
        // int -> Integer
        int i = 996;

        // 手动装箱
        Integer i1 = new Integer(i);
        Integer i2 = Integer.valueOf(i);
        log.info("{} {}", i1, i2);

        // 自动装箱，底层其实执行了 Integer i3 = Integer.valueOf(i);
        Integer i3 = i;
        log.info("{}", i3);
    }

    private static void testInteger() {
        Integer i1 = 1;
        Integer i2 = 1;
        log.info("i1 == i2 ? {}", i1 == i2); // true

        Integer i3 = 128;
        Integer i4 = 128;
        log.info("i3 == i4 ? {}", i3 == i4); // false
        // Integer.valueOf(int) 对值为 -128 ～ 127 的Integer对象做了缓存
    }

    private static void testUnboxed() {
        // 拆箱：包装类 -> 基本数据类型
        // Integer -> int

        // 自动装箱，底层执行 Integer i1 = Integer.valueOf(996);
        Integer i1 = 996;

        // 手动拆箱
        int i2 = i1.intValue();

        // 自动拆箱，底层其实执行了 int i3 = i1.intValue();
        int i3 = i1;

        log.info("{} {} {}", i1, i2, i3);
    }

    private static void testString() {
        // 基本数据类型 -> String
        // int -> String
        int i = 996;
        String s = String.valueOf(i);
        String s1 = Integer.toString(i);
        log.info("{} {}", s, s1);

        // 包装类 -> String
        // Integer -> String
        Integer i1 = new Integer(i);
        String s2 = i1.toString();
        log.info("{}", s2);
    }

    private static void testString1() {
        // String -> 基本数据类型
        // String -> int
        String s = "996";
        int i1 = Integer.parseInt(s);
        int i2 = new Integer(s).intValue();
        log.info("{} {}", i1, i2);
    }
}
