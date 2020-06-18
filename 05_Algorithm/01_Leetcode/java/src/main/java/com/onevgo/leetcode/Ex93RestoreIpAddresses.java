package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex93RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        backtrace(res, "", s, 0, 4);
        return res;
    }

    private void backtrace(List<String> res, String prev, String s, int start, int n) {
        if (n == 0) {
            if (start == s.length()) {
                res.add(prev);
            }
            return;
        }

        int sum = 0;
        int len = 0;
        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(start) == '0') {
                break;
            }
            sum = sum * 10 + (s.charAt(i) - '0');
            if (len > 2 || sum > 255) {
                break;
            }
            prev = prev + s.charAt(i);
            len++;
            backtrace(res, n == 1 ? prev : prev + ".", s, i + 1, n - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Ex93RestoreIpAddresses().restoreIpAddresses("25525511135"));
    }
}
