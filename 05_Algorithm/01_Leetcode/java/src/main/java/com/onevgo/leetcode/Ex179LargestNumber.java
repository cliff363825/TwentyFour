package com.onevgo.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Ex179LargestNumber {
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String order1 = o1 + o2;
                String order2 = o2 + o1;
                return order2.compareTo(order1);
            }
        });
        if (strings[0].equals("0")) {
            return "0";
        }
        return String.join("", strings);
    }

    public static void main(String[] args) {
        new Ex179LargestNumber().largestNumber(new int[]{1, 2, 3, 4, 5, 6, 10, 11, 12});
    }
}
