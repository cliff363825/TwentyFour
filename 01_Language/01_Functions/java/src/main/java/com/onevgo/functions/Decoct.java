package com.onevgo.functions;

public class Decoct {
    public static String decoct(int number) {
        return Long.toOctalString(number);
    }

    public static String decoct(long number) {
        return Long.toOctalString(number);
    }

    public static void main(String[] args) {
        System.out.println(decoct(15));
        System.out.println(decoct(-15));
        System.out.println(decoct(264));
        System.out.println(Integer.toOctalString(15));
        System.out.println(Integer.toOctalString(-15));
        System.out.println(Integer.toOctalString(264));
    }
}
