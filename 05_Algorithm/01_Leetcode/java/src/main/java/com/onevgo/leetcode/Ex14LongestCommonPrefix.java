package com.onevgo.leetcode;

public class Ex14LongestCommonPrefix {
    //编写一个函数来查找字符串数组中的最长公共前缀。
    //
    //如果不存在公共前缀，返回空字符串 ""。
    //
    //示例 1:
    //输入: ["flower","flow","flight"]
    //输出: "fl"
    //
    //示例 2:
    //输入: ["dog","racecar","car"]
    //输出: ""
    //解释: 输入不存在公共前缀。
    //
    //说明:
    //所有输入只包含小写字母 a-z 。
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // first string
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            // others
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
//        String[] strings = {"flower", "flow", "flight"};
        String[] strings = {"aa", "a"};
        System.out.println(new Ex14LongestCommonPrefix().longestCommonPrefix(strings));
    }
}
