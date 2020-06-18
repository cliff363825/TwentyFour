package com.onevgo.functions;

public class Doubleval {
    public static double doubleval(String s) {
        if (s == null || s.length() == 0) return 0D;
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
                return 0D;
            }
        }
        if (!digit) return 0D;
        if (sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        return Double.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(doubleval("122.34343The"));
        System.out.println(doubleval("+122.34343."));
        System.out.println(doubleval("-122.34343.1"));
        System.out.println(doubleval("-.34343.1"));
        System.out.println(doubleval("The122.34343"));
    }
}
