package com.onevgo.leetcode;

public class Ex44IsMatch {

    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;

        for (int i = 1; i <= plen; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)
                        || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[slen][plen];
    }

    public static void main(String[] args) {
        //"c"
        //"*?*"
        System.out.println(new Ex44IsMatch().isMatch("aa", "a"));
        System.out.println(new Ex44IsMatch().isMatch("aa", "*"));
        System.out.println(new Ex44IsMatch().isMatch("adceb", "*a*b"));
        System.out.println(new Ex44IsMatch().isMatch("aaaa", "***a"));
        System.out.println(new Ex44IsMatch().isMatch("c", "*?*"));
    }
}
