package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex151ReverseWords {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if ("".equals(words[i])) continue;
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        String s = "  hello world!  ";
        System.out.println(Arrays.toString(s.split(" ")));
    }
}
