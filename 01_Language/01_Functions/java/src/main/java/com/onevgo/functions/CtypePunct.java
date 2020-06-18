package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class CtypePunct {
    public static boolean ctypePunct(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // printable except letter,digit and blank
            if ((codePoint > 32 && codePoint <= 47) ||
                    (codePoint >= 58 && codePoint <= 64) ||
                    (codePoint >= 91 && codePoint <= 96) ||
                    (codePoint >= 123 && codePoint <= 126)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("ABasdk!@!$#", "!@ # $", "*&$()");
        for (String s : strings) {
            if (ctypePunct(s)) {
                System.out.println("The string " + s + " consists of all punctuation.");
            } else {
                System.out.println("The string " + s + " does not consist of all punctuation.");
            }
        }
    }
}
