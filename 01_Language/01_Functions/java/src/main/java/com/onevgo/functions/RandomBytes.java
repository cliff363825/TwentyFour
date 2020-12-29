package com.onevgo.functions;

import cn.hutool.core.util.RandomUtil;

public class RandomBytes {
    public static byte[] randomBytes(int length) {
        return RandomUtil.randomBytes(length);
    }

    public static void main(String[] args) {
        System.out.println(Bin2Hex.bin2Hex(randomBytes(5)));
    }
}
