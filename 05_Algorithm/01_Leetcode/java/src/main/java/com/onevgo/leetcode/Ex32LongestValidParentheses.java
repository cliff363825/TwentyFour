package com.onevgo.leetcode;

public class Ex32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        System.out.println(new Ex32LongestValidParentheses().longestValidParentheses(")()())"));
        System.out.println(new Ex32LongestValidParentheses().longestValidParentheses("(()"));
        System.out.println(new Ex32LongestValidParentheses().longestValidParentheses("()(()"));
    }
}
