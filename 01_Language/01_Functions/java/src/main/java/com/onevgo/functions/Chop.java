package com.onevgo.functions;

import com.google.common.base.CharMatcher;

public class Chop {
    public static String chop(String str) {
        return CharMatcher.whitespace().trimTrailingFrom(str);
    }

    public static String chop(String str, String characterMask) {
        return CharMatcher.anyOf(characterMask).trimTrailingFrom(str);
    }

    public static String chop(String str, char startInclusive, char endInclusive) {
        return CharMatcher.inRange(startInclusive, endInclusive).trimTrailingFrom(str);
    }

    public static void main(String[] args) {
        String text = "\t\tThese are a few words :) ...  ";
        String binary = (char) 9 + "Example string" + (char) 10;
        String hello = "Hello World";
        System.out.println(text);
        System.out.println(binary);
        System.out.println(hello);

        System.out.println(chop(text));
        System.out.println(chop(text, " \t."));
        System.out.println(chop(hello, "Hdle"));

        // trim the ASCII control characters at the end of $binary
        // (from 0 to 31 inclusive)
        System.out.println(chop(binary, (char) 0, (char) 31));
    }
}
