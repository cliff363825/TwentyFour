package com.onevgo.functions;

import java.nio.charset.StandardCharsets;

public class Hexdec {
    public static long hexdec(String hexString) {
        long result = 0;
        byte[] bytes = hexString.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            int digit = Character.digit(b, 16);
            if (digit >= 0) {
                result = (result << 4) + digit;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(hexdec("See"));
        System.out.println(hexdec("ee"));
        System.out.println(hexdec("that"));
        System.out.println(hexdec("thatthat"));
        System.out.println(hexdec("a0"));
    }
}
