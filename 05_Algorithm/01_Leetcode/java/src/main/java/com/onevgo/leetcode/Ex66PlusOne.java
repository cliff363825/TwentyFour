package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex66PlusOne {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] / 10 == 0) {
                return digits;
            }
            digits[i] = digits[i] % 10;
        }

        int[] res = new int[digits.length + 1];
        res[0] = 1;
        System.arraycopy(digits, 0, res, 1, digits.length);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Ex66PlusOne().plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})));
    }
}
