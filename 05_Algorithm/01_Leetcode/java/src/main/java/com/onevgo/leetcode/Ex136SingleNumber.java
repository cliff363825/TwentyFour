package com.onevgo.leetcode;

import java.util.HashMap;

public class Ex136SingleNumber {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, 1);
            }
        }

        return map.keySet().iterator().next();
    }

    public int singleNumber2(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            n = n ^ nums[i];
        }
        return n;
    }

    public static void main(String[] args) {

    }
}
