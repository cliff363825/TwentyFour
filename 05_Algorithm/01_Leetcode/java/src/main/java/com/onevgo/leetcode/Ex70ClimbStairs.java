package com.onevgo.leetcode;

public class Ex70ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 1) return n;
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public int climbStairs1(int n) {
        if (n <= 1) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(new Ex70ClimbStairs().climbStairs(2));
        System.out.println(new Ex70ClimbStairs().climbStairs(3));
    }
}
