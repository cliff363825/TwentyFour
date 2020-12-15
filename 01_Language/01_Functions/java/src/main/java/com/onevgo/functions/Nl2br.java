package com.onevgo.functions;

import java.util.regex.Pattern;

public class Nl2br {
    private static final Pattern NEW_LINE = Pattern.compile("(\r\n|\n\r|\n|\r)");

    public static String nl2br(String string) {
        return nl2br(string, true);
    }

    public static String nl2br(String string, boolean isXhtml) {
        return NEW_LINE.matcher(string).replaceAll(isXhtml ? "<br />$1" : "<br>$1");
    }

    public static void main(String[] args) {
        System.out.println(nl2br("foo isn't\n bar"));
        System.out.println(nl2br("foo isn't\r bar"));
        System.out.println(nl2br("foo isn't\r\n bar"));
        System.out.println(nl2br("foo isn't\r\n bar", false));
    }
}
