package com.onevgo.leetcode;

public class Ex91NumDecodings {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            char prev = s.charAt(i - 2);
            if (c != '0') {
                dp[i] = dp[i - 1];
            }
            if (prev == '0') {
                continue;
            }
            int num = (prev - '0') * 10 + (c - '0');
            if (num <= 26) {
                // c != '0': dp[i] = dp[i-1] + dp[i-2]
                // c == '0': dp[i] = dp[i-2]
                dp[i] = dp[i] + dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Ex91NumDecodings().numDecodings("12"));
        System.out.println(new Ex91NumDecodings().numDecodings("226"));
        System.out.println(new Ex91NumDecodings().numDecodings("100"));
        System.out.println(new Ex91NumDecodings().numDecodings("10"));
    }
}
