package com.onevgo.bitwise;

/**
 * 绝对值
 */
public class Abs {
    public static void main(String[] args) {
        int a = -15;
        int i = a >> 31;
        System.out.println(i == 0 ? a : ~a + 1);

        int b = -15;
        // b >= 0，b >> 31，即 0
        // b < 0, b >> 31，[11111111]补，即 -1
        // 也就是说，j的值为0（b为正数或0）或者-1（b为负数）
        // A ^ -1 = ~A
        int j = b >> 31;
        System.out.println((b ^ j) - j);
    }
}
