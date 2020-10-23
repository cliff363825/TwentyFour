package com.onevgo.functions;

public class IsBool {
    public static boolean isBool(Object var) {
        return var instanceof Boolean;
    }

    public static void main(String[] args) {
        boolean a = false;
        int b = 0;
        System.out.println(isBool(a));
        System.out.println(isBool(b));
    }
}
