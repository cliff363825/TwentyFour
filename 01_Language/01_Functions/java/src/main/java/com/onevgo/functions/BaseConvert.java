package com.onevgo.functions;

public class BaseConvert {
    public static String baseConvert(String s, int fromBase, int toBase) {
        if (fromBase < 2 || fromBase > 36 || toBase < 2 || toBase > 36) return null;
        try {
            return Integer.toString(Integer.parseInt(s, fromBase), toBase);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(baseConvert("a37334", 16, 2));
    }
}
