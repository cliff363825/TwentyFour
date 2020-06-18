package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class CtypeXdigit {
    public static boolean ctypeXdigit(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // 0-9 or a-f or A-F
            if ((codePoint >= 48 && codePoint <= 57) ||
                    (codePoint >= 65 && codePoint <= 70) ||
                    (codePoint >= 97 && codePoint <= 102)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("AB10BC99", "AR1012", "ab12bc99");
        for (String s : strings) {
            if (ctypeXdigit(s)) {
                System.out.println("The string " + s + " consists of all hexadecimal digits.");
            } else {
                System.out.println("The string " + s + " does not consist of all hexadecimal digits");
            }
        }
    }
}
