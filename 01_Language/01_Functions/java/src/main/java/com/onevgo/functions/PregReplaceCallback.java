package com.onevgo.functions;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PregReplaceCallback {
    public static String pregReplaceCallback(String pattern, Function<Matcher, String> callback, String subject) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(subject);
        boolean result = matcher.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            do {
                matcher.appendReplacement(sb, callback.apply(matcher));
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }

        return subject;
    }

    public static String pregReplaceCallback(Pattern pattern, Function<MatchResult, String> callback, String subject) {
        Matcher matcher = pattern.matcher(subject);
        boolean result = matcher.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            do {
                matcher.appendReplacement(sb, callback.apply(matcher.toMatchResult()));
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }

        return subject;
    }

    public static void main(String[] args) {
        String text = "April fools day is 04/01/2002,aaaa\n" +
                "Last christmas was 12/24/2001,bbbb\n";

        Pattern pattern = Pattern.compile("(\\d{2}/\\d{2}/)(\\d{4})");

        System.out.println(pregReplaceCallback(pattern, m -> m.group(1) + (Integer.parseInt(m.group(2)) + 1), text));
    }
}
