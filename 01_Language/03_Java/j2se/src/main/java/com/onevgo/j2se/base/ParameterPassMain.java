package com.onevgo.j2se.base;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ParameterPassMain {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Example example = new Example();

        change(i, str, num, arr, example);

        log.info("i={}", i); // 1
        log.info("str={}", str); // hello
        log.info("num={}", num); // 200
        log.info("arr={}", Arrays.toString(arr)); // [2, 2, 3, 4, 5]
        log.info("example={}", example);

        // 方法的参数传递机制 —— 按值传递
        // 1. 基本数据类型：值拷贝
        // 2. 引用数据类型：地址拷贝
        //   特殊的类型：String、包装类等对象不可变性
    }

    private static void change(int j, String s, Integer n, int[] a, Example example) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        example.a++;
    }

}
