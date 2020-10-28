package com.onevgo.functions;

public class IsFloat {
    public static boolean isFloat(Object var) {
        return var instanceof Float;
    }

    public static void main(String[] args) {
        System.out.println(isFloat(27.25));
        System.out.println(isFloat("abc"));
        System.out.println(isFloat(23));
        System.out.println(isFloat(23.5));
        System.out.println(isFloat(1e7));
        System.out.println(isFloat(true));
    }
}
