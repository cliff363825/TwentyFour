package com.onevgo.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSlashes {
    private static final Pattern PATTERN = Pattern.compile("[\\\\'\"\u0000]");

    public static String addSlashes(String s) {
        Matcher matcher = PATTERN.matcher(s);
        boolean result = matcher.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            do {
                matcher.appendReplacement(sb, "\u0000".equals(matcher.group(0)) ? "\\\\0" : "\\\\$0");
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        String str = "' \" \\ \0";
        System.out.println(str);
        System.out.println(addSlashes(str));
    }
}
