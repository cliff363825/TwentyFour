package com.onevgo.functions;

import java.util.regex.Pattern;

public class PregReplace {
    public static String pregReplace(String pattern, String replacement, String subject) {
        return pregReplace(Pattern.compile(pattern), replacement, subject);
    }

    public static String pregReplace(Pattern pattern, String replacement, String subject) {
        return pattern.matcher(subject).replaceAll(replacement);
    }

    public static void main(String[] args) {
        String string = "April 15, 2003";
        String pattern = "(\\w+) (\\d+), (\\d+)";
        String replacement = "$1\\1,$3";
        System.out.println(pregReplace(pattern, replacement, string));
    }
}
