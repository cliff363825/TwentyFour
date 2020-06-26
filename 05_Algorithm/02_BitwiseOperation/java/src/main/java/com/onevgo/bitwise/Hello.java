package com.onevgo.bitwise;

public class Hello {
    public static void main(String[] args) {
        int a = -15, b = 15;

        // -15 = [11110001]补，算术右移两位，最高位由符号位填充将得到 [11111100]补 即-4
        System.out.println(a >> 2);
        // -15 = [11111111 11111111 11111111 11110001]补，逻辑右移两位，最高位由0填充将得到 [00111111 11111111 11111111 11111100]补 即1073741820
        System.out.println(a >>> 2);
        System.out.println(Integer.parseInt("00111111111111111111111111111100", 2));

        // 15 = [00001111]补，算术右移两位，最高位由符号位填充将得到 [00000011]补，即3
        System.out.println(b >> 2);
        // 15 = [00001111]补，逻辑右移两位，最高位由0填充将得到 [00000011]补，即3
        System.out.println(b >>> 2);
    }
}
