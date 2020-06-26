package com.onevgo.bitwise;

/**
 * 变换符号
 * -A = ~A + 1
 * A ^ -1 = ~A
 */
public class Sign {
    public static void main(String[] args) {
        int a = -15, b = 15;

        // -15 = [11110001]补，取反(~) = [00001110]补，+1 = [00001111]补，即15
        System.out.println(~a + 1);
        // 15 = [00001111]补，取反(~) = [11110000]补，+1 = [11110001]补，即-15
        System.out.println(~b + 1);

        // 变化符号：取反(~) + 1
        // -A = ~A + 1
    }
}
