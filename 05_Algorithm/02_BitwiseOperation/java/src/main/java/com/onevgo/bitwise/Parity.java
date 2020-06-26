package com.onevgo.bitwise;

/**
 * 判断奇偶数
 * 奇数 A & 1 == 1
 * 偶数 A & 1 == 0
 */
public class Parity {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 1) {
                // 奇数
                System.out.println(i + "是奇数");
            } else {
                // 偶数
                System.out.println(i + "是偶数");
            }
        }
    }
}
