package com.onevgo.leetcode;

public class Ex28StrStr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) return 0;

        char[] srcArr = haystack.toCharArray();
        char[] destArr = needle.toCharArray();

        for (int i = 0; i <= srcArr.length - destArr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < destArr.length; j++) {
                if (srcArr[i + j] != destArr[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Ex28StrStr().strStr("hello", "ll"));
    }
}
