package com.onevgo.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Ex71SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        int i = 1;
        int j = 1;
        int len = path.length();
        while (i <= len) {
            if (i == len || path.charAt(i) == '/') {
                String s = path.substring(j, i);
                if ("..".equals(s)) {
                    if (!deque.isEmpty()) deque.pop();
                } else if (!"".equals(s) && !".".equals(s)) {
                    deque.push(s);
                }
                j = i + 1;
            }
            i++;
        }
        StringBuilder sb = new StringBuilder("/");
        while (!deque.isEmpty()) {
            sb.append(deque.removeLast()).append("/");
        }
        String s = sb.toString();
        return s.length() > 1 ? s.substring(0, s.length() - 1) : s;
    }

    public static void main(String[] args) {
        System.out.println(new Ex71SimplifyPath().simplifyPath("/home//foo/"));
//        System.out.println(new Ex71SimplifyPath().simplifyPath("/../"));
    }
}
