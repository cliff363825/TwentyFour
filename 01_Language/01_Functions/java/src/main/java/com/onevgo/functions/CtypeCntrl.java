package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;

public class CtypeCntrl {
    public static boolean ctypeCntrl(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // 0-31 or 127
            if ((codePoint >= 0 && codePoint <= 31) ||
                    codePoint == 127) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("string1", "\n\r\t");
        map.put("string2", "arf12");
        map.put("", "");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (ctypeCntrl(entry.getValue())) {
                System.out.println("The string " + entry.getKey() + " consists of all control characters.");
            } else {
                System.out.println("The string " + entry.getKey() + " does not consist of all control characters.");
            }
        }
    }
}
