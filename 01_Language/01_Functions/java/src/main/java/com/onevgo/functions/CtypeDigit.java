package com.onevgo.functions;

import java.util.Arrays;
import java.util.List;

public class CtypeDigit {
    public static boolean ctypeDigit(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 0-9
            if (c >= 48 && c <= 57) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1820.20", "10002", "wsl!12");
        for (String s : strings) {
            if (ctypeDigit(s)) {
                System.out.println("The string " + s + " consists of all digits.");
            } else {
                System.out.println("The string " + s + " does not consist of all digits.");
            }
        }
    }
}
