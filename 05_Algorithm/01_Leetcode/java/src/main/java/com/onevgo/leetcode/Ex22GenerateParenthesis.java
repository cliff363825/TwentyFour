package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex22GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        // 回溯算法
        // n=3
        // ( -> ( -> ( -> ) -> ) -> )
        //        -> ) -> ( -> ) -> )
        //             -> ) -> ( -> )
        ArrayList<String> res = new ArrayList<>();
        backtrace(res, "", 0, 0, n);
        return res;
    }

    private void backtrace(List<String> list, String s, int leftNum, int rightNum, int n) {
        if (leftNum + rightNum == 2 * n) {
            list.add(s);
            return;
        }
        if (leftNum < n) {
            backtrace(list, s + "(", leftNum + 1, rightNum, n);
        }
        if (rightNum < leftNum) {
            backtrace(list, s + ")", leftNum, rightNum + 1, n);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Ex22GenerateParenthesis().generateParenthesis(3));
    }
}
