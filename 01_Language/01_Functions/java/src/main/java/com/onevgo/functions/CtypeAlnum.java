package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class CtypeAlnum {
    public static boolean ctypeAlnum(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // A-Z or a-Z or 0-9
            if ((codePoint >= 65 && codePoint <= 90) ||
                    (codePoint >= 97 && codePoint <= 122) ||
                    (codePoint >= 48 && codePoint <= 57)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("AbCd1zyZ9", "foo!#$bar", "");
        for (String s : strings) {
            if (ctypeAlnum(s)) {
                System.out.println("The string " + s + " consists of all letters or digits.");
            } else {
                System.out.println("The string " + s + " does not consist of all letters or digits.");
            }
        }
    }
}
