package com.onevgo.functions;

import cn.hutool.core.util.RandomUtil;

public class Rand {
    public static int rand() {
        return RandomUtil.randomInt();
    }

    public static int rand(int min, int max) {
        return RandomUtil.randomInt(min, max);
    }

    public static void main(String[] args) {
        System.out.println(rand());
        System.out.println(rand());
        System.out.println(rand(5, 15));
    }
}
