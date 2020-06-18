package com.onevgo.functions;

public class CtypeUpper {
    public static boolean ctypeUpper(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char codePoint = s.charAt(i);
            // A-Z
            if (codePoint >= 65 && codePoint <= 90) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
