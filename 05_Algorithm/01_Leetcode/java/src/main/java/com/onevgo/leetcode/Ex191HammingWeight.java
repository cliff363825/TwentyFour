package com.onevgo.leetcode;

public class Ex191HammingWeight {
    public int hammingWeight(int n) {
        int num = 0;
        while (n != 0) {
            num += n & 1;
            n = n >>> 1;
        }
        return num;
    }
}
