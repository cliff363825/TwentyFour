package com.onevgo.functions;

import java.util.regex.Pattern;

public class PregMatch {
    public static boolean pregMatch(String pattern, String subject) {
        Pattern compile = Pattern.compile(pattern);
        return compile.matcher(subject).find();
    }

    public static boolean pregMatch(Pattern pattern, String subject) {
        return pattern.matcher(subject).find();
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("php", Pattern.CASE_INSENSITIVE);
        System.out.println(pregMatch(pattern, "PHP is the web scripting language of choice."));
    }
}
