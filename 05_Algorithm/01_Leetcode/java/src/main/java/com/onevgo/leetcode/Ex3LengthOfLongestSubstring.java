package com.onevgo.leetcode;

public class Ex3LengthOfLongestSubstring {
    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    //
    //示例 1:
    //输入: "abcabcbb"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    //
    //示例 2:
    //输入: "bbbbb"
    //输出: 1
    //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    //
    //示例 3:
    //输入: "pwwkew"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < s.length(); j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        // "abcabcbb" 3
        // "bbbbb" 1
        // "pwwkew" 3
        // abcdefghd
        System.out.println(new Ex3LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Ex3LengthOfLongestSubstring().lengthOfLongestSubstring(" "));
        System.out.println(new Ex3LengthOfLongestSubstring().lengthOfLongestSubstring("dvdf"));
        System.out.println(new Ex3LengthOfLongestSubstring().lengthOfLongestSubstring("abba"));
    }
}
