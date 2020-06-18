package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex10IsMatch {
    //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
    //
    //'.' 匹配任意单个字符
    //'*' 匹配零个或多个前面的那一个元素
    //所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
    //
    //说明:
    //s 可能为空，且只包含从 a-z 的小写字母。
    //p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
    //
    //示例 1:
    //输入:
    //s = "aa"
    //p = "a"
    //输出: false
    //解释: "a" 无法匹配 "aa" 整个字符串。
    //
    //示例 2:
    //输入:
    //s = "aa"
    //p = "a*"
    //输出: true
    //解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
    //
    //示例 3:
    //输入:
    //s = "ab"
    //p = ".*"
    //输出: true
    //解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
    //
    //示例 4:
    //输入:
    //s = "aab"
    //p = "c*a*b"
    //输出: true
    //解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
    //
    //示例 5:
    //输入:
    //s = "mississippi"
    //p = "mis*is*p*."
    //输出: false
    public boolean isMatch(String text, String pattern) {
        // f(n,m) = text[n:] == pattern[m:] if n==len(text) and m==len(pattern): True
        //        = text[n-1:n] == pattern[m-1:m] and f(n-1,m-1) or
        //          pattern[m-1:m] == '*' and f(n,m-2)
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean match = (i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (match && dp[i + 1][j]);
                } else {
                    dp[i][j] = match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        //"aab"
        //"c*a*b"
//        System.out.println(new Ex10IsMatch().isMatch("aa", "a"));
        System.out.println(new Ex10IsMatch().isMatch("ab", ".*"));
//        System.out.println(isMatch("ab", ".*c"));
    }
}
