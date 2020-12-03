package com.onevgo.functions;

import com.google.common.base.CharMatcher;

public class Ltrim {
    public static String ltrim(CharSequence str) {
        return CharMatcher.whitespace().trimLeadingFrom(str);
    }

    public static String ltrim(CharSequence str, CharSequence charlist) {
        return CharMatcher.anyOf(charlist).trimLeadingFrom(str);
    }

    public static String ltrim(CharSequence str, char startInclusive, char endInclusive) {
        return CharMatcher.inRange(startInclusive, endInclusive).trimLeadingFrom(str);
    }

    public static void main(String[] args) {
        String text = "\t\tThese are a few words :) ... ";
        String binary = "\010Example string\012";
        String hello = "Hello World";

        System.out.println(text);
        System.out.println(binary);
        System.out.println(hello);

        String trimmed = ltrim(text);
        System.out.println(trimmed);

        trimmed = ltrim(text, " \t.");
        System.out.println(trimmed);

        trimmed = ltrim(hello, "Hdle");
        System.out.println(trimmed);

        // trim the ASCII control characters at the beginning of $binary
        // (from 0 to 31 inclusive)
        String clean = ltrim(binary, (char) 0, (char) 31);
        System.out.println(clean);
    }
}
