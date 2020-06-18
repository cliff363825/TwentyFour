package com.onevgo.leetcode;

public class Ex171TitleToNumber {
    public int titleToNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            num = num * 26 + (c - 'A' + 1);
        }
        return num;
    }
    public static void main(String[] args) {

    }
}
