package com.onevgo.leetcode;

public class Ex125IsPalindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char c1 = s.charAt(start);
            char c2 = s.charAt(end);
            if (isAlnum(c1) && isAlnum(c2)) {
                if (c1 != c2) {
                    return false;
                }
                start++;
                end--;
            } else if (isAlnum(c1)) {
                end--;
            } else if (isAlnum(c2)) {
                start++;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    private boolean isAlnum(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public static void main(String[] args) {
        System.out.println(new Ex125IsPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
    }
}
