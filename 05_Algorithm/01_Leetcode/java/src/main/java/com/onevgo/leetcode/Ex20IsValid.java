package com.onevgo.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Ex20IsValid {
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //
    //有效字符串需满足：
    //
    //左括号必须用相同类型的右括号闭合。
    //左括号必须以正确的顺序闭合。
    //注意空字符串可被认为是有效字符串。
    //
    //示例 1:
    //输入: "()"
    //输出: true
    //
    //示例 2:
    //输入: "()[]{}"
    //输出: true
    //
    //示例 3:
    //输入: "(]"
    //输出: false
    //
    //示例 4:
    //输入: "([)]"
    //输出: false
    //
    //示例 5:
    //输入: "{[]}"
    //输出: true
    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    deque.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    if (deque.isEmpty() ||
                            (c == ')' && deque.pop() != '(') ||
                            (c == '}' && deque.pop() != '{') ||
                            (c == ']' && deque.pop() != '[')
                    ) {
                        return false;
                    }
                    break;
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {

    }
}
