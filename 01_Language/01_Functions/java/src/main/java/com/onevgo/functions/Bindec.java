package com.onevgo.functions;

import java.math.BigInteger;

public class Bindec {
    public static String bindec(String s) {
        return new BigInteger(s, 2).toString(10);
    }

    public static void main(String[] args) {
        System.out.println(bindec("110011"));
        System.out.println(bindec("000110011"));
        System.out.println(bindec("111"));
    }
}
