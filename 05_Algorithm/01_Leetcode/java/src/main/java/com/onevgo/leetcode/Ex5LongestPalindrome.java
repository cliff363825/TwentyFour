package com.onevgo.leetcode;

public class Ex5LongestPalindrome {
    //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //
    //示例 1：
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。
    //
    //示例 2：
    //输入: "cbbd"
    //输出: "bb"
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd
            int len1 = expandAroundCenter(s, i, i);
            // even
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                // ensure end >= start
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        //输入: "babad"
        //输出: "bab"
        //注意: "aba" 也是一个有效答案。
        // abcdcbabcdefedcba
        // abba
        // abcacba
        //输入: "cbbd"
        //输出: "bb"
//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome("a"));
//        System.out.println(longestPalindrome(""));
        System.out.println(new Ex5LongestPalindrome().longestPalindrome("cbbd"));
//        System.out.println(longestPalindrome("ccc"));
//        System.out.println(longestPalindrome("abcdcbabcdefedcba"));
    }
}
