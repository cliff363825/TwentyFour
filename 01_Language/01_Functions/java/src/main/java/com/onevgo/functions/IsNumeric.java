package com.onevgo.functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IsNumeric {
    public static boolean isNumeric(Object var) {
        if (var == null) {
            return false;
        }
        if (var instanceof Number) {
            return true;
        }
        if (var instanceof String) {
            String s = (String) var;
            boolean digit = false;
            boolean dot = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    if (digit) {
                        return false;
                    }
                } else if (c == '+' || c == '-') {
                    if (digit) {
                        return false;
                    }
                    digit = true;
                } else if (c >= '0' && c <= '9') {
                    digit = true;
                } else if (c == 'e') {
                    if (!digit || i == s.length() - 1) {
                        return false;
                    }
                } else if (c == '.') {
                    if (dot) {
                        return false;
                    }
                    dot = true;
                    digit = true;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<Object> tests = Arrays.asList("42",
                1337,
                0x539,
                02471,
                0b10100111001,
                1337e0,
                "0x539",
                "02471",
                " 02471",
                "02471 ",
                "02 471",
                "0b10100111001",
                "1337e0",
                "e0",
                "0e",
                "not numeric",
                Collections.emptyList(),
                9.1,
                "9.1",
                "9.",
                ".1",
                null);
        for (Object s : tests) {
            if (isNumeric(s)) {
                System.out.println(s + " is numeric");
            } else {
                System.out.println(s + " is not numeric");
            }
        }
    }
}
