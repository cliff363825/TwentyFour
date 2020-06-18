package com.onevgo.leetcode;

public class Ex69MySqrt {
    public int mySqrt(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long) pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }

    public static void main(String[] args) {
        System.out.println(new Ex69MySqrt().mySqrt(4));
        System.out.println(new Ex69MySqrt().mySqrt(8));
        System.out.println(new Ex69MySqrt().mySqrt(2147395599));
    }
}
