package com.onevgo.functions;

public class IsDouble {
    public static boolean isDouble(Object var) {
        return var != null && var.getClass() == Double.class;
    }

    public static void main(String[] args) {
        System.out.println(isDouble(27.25));
        System.out.println(isDouble("abc"));
        System.out.println(isDouble(23));
        System.out.println(isDouble(23.5));
        System.out.println(isDouble(1e7));
        System.out.println(isDouble(true));
    }
}
