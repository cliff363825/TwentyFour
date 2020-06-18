package com.onevgo.leetcode;

public class Ex168ConvertToTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            n = n - 1;
            int r = n % 26;
            sb.insert(0, (char) ('A' + r));
            n /= 26;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
