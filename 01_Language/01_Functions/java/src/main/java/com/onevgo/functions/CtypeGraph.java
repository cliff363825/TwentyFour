package com.onevgo.functions;

import java.util.LinkedHashMap;
import java.util.Map;

public class CtypeGraph {
    public static boolean ctypeGraph(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // printable except space
            if (codePoint > 32 && codePoint <= 126) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("string1", "asdf\n\r\t");
        map.put("string2", "arf12");
        map.put("string3", "LKA#@%.54");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (ctypeGraph(entry.getValue())) {
                System.out.println("The string " + entry.getKey() + " consists of all (visibly) printable characters.");
            } else {
                System.out.println("The string " + entry.getKey() + " does not consist of all (visibly) printable characters.");
            }
        }
    }
}
