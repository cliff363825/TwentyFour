package com.onevgo.leetcode;

public class Ex172TrailingZeroes {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }
    public static void main(String[] args) {
    }
}
