package com.onevgo.leetcode;

public class Ex50MyPow {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    public double powCore(double x, int n) {
        if (n == 1) return x;
        if (n % 2 == 1) {
            return x * powCore(x, n / 2) * powCore(x, n / 2);
        } else {
            return powCore(x, n / 2) * powCore(x, n / 2);
        }
    }

    public double fastPow(double x, long N) {
        double ans = 1;
        double current_product = x;
        // 折半相乘
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new Ex50MyPow().myPow(2, 10));
//        System.out.println(new Ex50MyPow().myPow(2.1, 3));
//        System.out.println(new Ex50MyPow().myPow(2.0, -2));
        //0.00001
        //2147483647
//        System.out.println(Integer.MAX_VALUE); // 2147483647
//        System.out.println(new Ex50MyPow().myPow(0.00001, 2147483647));
        System.out.println(new Ex50MyPow().myPow(2, -2147483648));
    }
}
