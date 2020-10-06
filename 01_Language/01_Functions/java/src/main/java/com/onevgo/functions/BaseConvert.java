package com.onevgo.functions;

import java.math.BigInteger;

public class BaseConvert {
    public static String baseConvert(String number, int frombase, int tobase) {
        return new BigInteger(number, frombase).toString(tobase);
    }

    public static void main(String[] args) {
        System.out.println(baseConvert("a37334", 16, 2));
    }
}
