package com.onevgo.functions;

public class FloatVal {
    public static float floatval(String s) {
        if (s == null || s.length() == 0) return 0F;
        StringBuilder sb = new StringBuilder();
        boolean digit = false;
        boolean sign = false;
        boolean dot = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
                digit = true;
            } else if (c == '+' || c == '-') {
                if (digit || sign || dot) break;
                sb.append(c);
                sign = true;
            } else if (c == '.') {
                if (dot) break;
                if (!digit) {
                    sb.append('0');
                    digit = true;
                }
                sb.append(c);
                dot = true;
            } else if (c == ' ') {
                if (digit || sign || dot) break;
            } else {
                if (digit || sign || dot) break;
                return 0F;
            }
        }
        if (!digit) return 0F;
        if (sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        return Float.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(floatval("122.34343The"));
        System.out.println(floatval("+122.34343."));
        System.out.println(floatval("-122.34343.1"));
        System.out.println(floatval("-.34343.1"));
        System.out.println(floatval("The122.34343"));
    }
}
