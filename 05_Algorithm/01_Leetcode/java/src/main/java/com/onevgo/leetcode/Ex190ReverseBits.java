package com.onevgo.leetcode;

public class Ex190ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 1; i <= 32; i++) {
            res += (n & 1) << (32 - i);
            n = n >> 1;
            if (n == 0) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Ex190ReverseBits().reverseBits(43261596));
        System.out.println(new Ex190ReverseBits().reverseBits(-3));
    }
}
