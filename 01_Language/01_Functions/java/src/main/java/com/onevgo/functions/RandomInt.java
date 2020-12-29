package com.onevgo.functions;

import cn.hutool.core.util.RandomUtil;

public class RandomInt {
    public static int randomInt(int min, int max) {
        return RandomUtil.randomInt(min, max);
    }

    public static void main(String[] args) {
        System.out.println(randomInt(100, 999));
        System.out.println(randomInt(-1000, 0));
    }
}
