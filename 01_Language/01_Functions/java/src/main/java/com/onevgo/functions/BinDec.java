package com.onevgo.functions;

public class BinDec {
    public static String binDec(String s) {
        return Integer.toString(Integer.parseInt(s, 2));
    }

    public static void main(String[] args) {
        System.out.println(binDec("110011"));
        System.out.println(binDec("000110011"));
        System.out.println(binDec("111"));
    }
}
