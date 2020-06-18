package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;

public class CtypeSpace {
    public static boolean ctypeSpace(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // space, tab, vertical tab, line feed, carriage return and form feed characters.
            if (codePoint == 32 || codePoint == 9 || codePoint == 11 ||
                    codePoint == 10 || codePoint == 13 || codePoint == 12) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("string1", "\n\r\t");
        map.put("string2", "\narf12");
        map.put("string3", "\\n\\r\\t");
        map.put("string4", " ");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (ctypeSpace(entry.getValue())) {
                System.out.println("The string " + entry.getKey() + " consists of whitespace characters only.");
            } else {
                System.out.println("The string " + entry.getKey() + " contains non-whitespace characters.");
            }
        }
    }
}
