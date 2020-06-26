package com.onevgo.bitwise;

/**
 * 交换两数
 * A = A ^ B
 * B = B ^ A = B ^ (A ^ B) = A
 * A = A ^ B = (A ^ B) ^ A = B
 */
public class Swap {
    public static void main(String[] args) {
        int a = 1, b = 2;

        a = a ^ b; // a = a ^ b
        b = b ^ a; // b = b ^ a = b ^ ( a ^ b ) = b ^ b ^ a = 0 ^ a = a
        a = a ^ b; // a = a ^ b = ( a ^ b ) ^ a = a ^ a ^ b = 0 ^ b = b

        // 异或运算满足
        // 1. 交换律，即 A ^ B = B ^ A
        // 2. 结合律，即 (A ^ B) ^ C = A ^ (B ^ C)
        // 3. 对于任何数，都有 A ^ A = 0, A ^ 0 = A
        // 4. 自反性，A ^ B ^ B = A ^ 0 = A

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
