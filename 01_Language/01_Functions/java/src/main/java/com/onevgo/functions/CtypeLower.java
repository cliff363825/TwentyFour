package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class CtypeLower {
    public static boolean ctypeLower(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // a-z
            if (codePoint >= 97 && codePoint <= 122) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("aac123", "qiutoas", "QASsdks");
        for (String s : strings) {
            if (ctypeLower(s)) {
                System.out.println("The string " + s + " consists of all lowercase letters.");
            } else {
                System.out.println("The string " + s + " does not consist of all lowercase letters.");
            }
        }
    }
}
