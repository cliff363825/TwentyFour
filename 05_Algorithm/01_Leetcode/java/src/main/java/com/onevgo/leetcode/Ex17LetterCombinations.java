package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex17LetterCombinations {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    //
    //示例:
    //输入："23"
    //输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    //说明:
    //尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", digits);
        return ans;
    }

    public void backtrack(List<String> ans, String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            ans.add(combination);
            return;
        }
        // if there are still digits to check

        // iterate over all letters which map
        // the next available digit
        String digit = next_digits.substring(0, 1);
        String letters = phone.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            String letter = phone.get(digit).substring(i, i + 1);
            // append the current letter to the combination
            // and proceed to the next digits
            backtrack(ans, combination + letter, next_digits.substring(1));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Ex17LetterCombinations().letterCombinations("234"));
    }
}
