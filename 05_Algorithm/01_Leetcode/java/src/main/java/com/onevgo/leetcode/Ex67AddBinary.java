package com.onevgo.leetcode;

public class Ex67AddBinary {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n = 0;
            if (i >= 0) {
                n += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                n += b.charAt(j) - '0';
                j--;
            }
            n += carry;
            carry = n / 2;
            sb.append(n % 2);
        }
        if (carry > 0) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Ex67AddBinary().addBinary("11", "1"));
        System.out.println(new Ex67AddBinary().addBinary("1010", "1011"));
    }
}
