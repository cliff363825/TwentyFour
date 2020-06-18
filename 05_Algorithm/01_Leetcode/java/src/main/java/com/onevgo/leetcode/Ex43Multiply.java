package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex43Multiply {
    public String multiply(String num1, String num2) {
        // 123 // 999 * 999 = 999000
        // 456
        // 123
        //   6
        //  18
        // 12
        // 6
        int[] ints = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >= 0; i--) {
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n1 = num1.charAt(j) - '0'; // 3 // 2
                int n2 = num2.charAt(i) - '0'; // 6
                ints[i + j + 1] += n1 * n2; // 18 // 12
            }
        }
        for (int i = ints.length - 1; i >= 0; i--) {
            if (ints[i] >= 10) {
                // 进位
                ints[i - 1] += ints[i] / 10;
                ints[i] = ints[i] % 10;
            }
        }

        int index = 0;
        while (index < ints.length && ints[index] == 0) {
            index++;
        }
        if (index == ints.length) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = index; i < ints.length; i++) {
            sb.append(ints[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Ex43Multiply().multiply("123", "456"));
        System.out.println(new Ex43Multiply().multiply("0", "0"));
    }
}
