package com.onevgo.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PregMatchAll {
    public static boolean pregMatchAll(String pattern, String subject) {
        return pregMatchAll(Pattern.compile(pattern), subject);
    }

    public static boolean pregMatchAll(Pattern pattern, String subject) {
        Matcher matcher = pattern.matcher(subject);
        return matcher.find();
    }

    public static boolean pregMatchAll(String pattern, String subject, List<List<String>> matches) {
        return pregMatchAll(Pattern.compile(pattern), subject, matches);
    }

    public static boolean pregMatchAll(Pattern pattern, String subject, List<List<String>> matches) {
        Matcher matcher = pattern.matcher(subject);
        boolean b = false;
        while (matcher.find()) {
            for (int i = 0, j = matcher.groupCount(); i <= j; i++) {
                if (i > matches.size() - 1) {
                    matches.add(new ArrayList<>());
                }
                matches.get(i).add(matcher.group(i));
            }
            b = true;
        }
        return b;
    }

    public static void main(String[] args) {
        List<List<String>> matches = new ArrayList<>();
        System.out.println(pregMatchAll("\\(?(?:(\\d{3})-)?\\)?\\d{3}-\\d{4}", "Call 555-1212 or 1-800-555-1212", matches));
        System.out.println(matches);
    }
}
