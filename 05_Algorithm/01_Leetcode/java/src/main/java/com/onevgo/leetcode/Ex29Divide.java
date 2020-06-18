package com.onevgo.leetcode;

public class Ex29Divide {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegetive = (dividend ^ divisor) < 0;
        int result = 0;
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        for (int i = 31; i >= 0; i--) {
            if (t >>> i >= d) {
                result += 1 << i;
                t -= d << i;
            }
        }
        return isNegetive ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println(new Ex29Divide().divide(10, 3));
        System.out.println(new Ex29Divide().divide(7, -3));
    }
}
