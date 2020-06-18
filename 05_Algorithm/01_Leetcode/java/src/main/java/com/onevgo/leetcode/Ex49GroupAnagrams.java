package com.onevgo.leetcode;

import java.util.*;

public class Ex49GroupAnagrams {
    //输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
    //输出:
    //[
    //  ["ate","eat","tea"],
    //  ["nat","tan"],
    //  ["bat"]
    //]
    //
    public List<List<String>> groupAnagrams(String[] strs) {
        //key=a,b,c,d,e,f,g......z
        //   =0,0,0,0,0,0,0,.....0
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(count[i]);
                sb.append(',');
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        int[] table = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        HashMap<Long, List<String>> map = new HashMap<>();
        List<List<String>> ret = new ArrayList<>();


        for (String s : strs) {
            long sum = 1L;
            for (char c : s.toCharArray()) {
                sum *= table[c - 'a'];
            }

            List<String> list = map.get(sum);
            if (null == list) {
                list = new ArrayList<>();
                list.add(s);
                map.put(sum, list);
                ret.add(list);
            } else {
                list.add(s);
            }
        }

        return ret;
    }

    public static void main(String[] args) {

    }
}
