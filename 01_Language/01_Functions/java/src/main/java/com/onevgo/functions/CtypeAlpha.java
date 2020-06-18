package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class CtypeAlpha {
    public static boolean ctypeAlpha(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // A-Z or a-z
            if ((codePoint >= 65 && codePoint <= 90) ||
                    (codePoint >= 97 && codePoint <= 122)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("KjgWZC", "arf12", "");
        for (String s : strings) {
            if (ctypeAlpha(s)) {
                System.out.println("The string " + s + " consists of all letters.");
            } else {
                System.out.println("The string " + s + " does not consist of all letters.");
            }
        }
    }
}
