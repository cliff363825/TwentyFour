package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Ex187FindRepeatedDnaSequences {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> res = new HashSet<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String s1 = s.substring(i, i + 10);
            if (set.contains(s1)) {
                res.add(s1);
            }
            set.add(s1);
        }
        return new ArrayList<>(res);
    }
    public static void main(String[] args) {

    }
}
