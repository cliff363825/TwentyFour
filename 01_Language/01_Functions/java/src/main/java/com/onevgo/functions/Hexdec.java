package com.onevgo.functions;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Hexdec {
    public static BigInteger hexdec(String hexString) {
        Pattern pattern = Pattern.compile("[^0-9a-fA-F]");
        hexString = pattern.matcher(hexString).replaceAll("");
        return new BigInteger(hexString, 16);
    }

    public static void main(String[] args) {
        System.out.println(hexdec("See"));
        System.out.println(hexdec("ee"));
        System.out.println(hexdec("that"));
        System.out.println(hexdec("a0"));
    }
}
