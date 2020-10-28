package com.onevgo.functions;

public class IsInt {
    public static boolean isInt(Object var) {
        return var instanceof Integer;
    }

    public static void main(String[] args) {
        System.out.println(isInt(23));
        System.out.println(isInt("23"));
        System.out.println(isInt(23.5));
        System.out.println(isInt("23.5"));
        System.out.println(isInt(null));
        System.out.println(isInt(true));
        System.out.println(isInt(false));
    }
}
