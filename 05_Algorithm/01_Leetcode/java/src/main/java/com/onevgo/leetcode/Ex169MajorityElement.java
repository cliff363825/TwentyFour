package com.onevgo.leetcode;


public class Ex169MajorityElement {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            count += (candidate == nums[i] ? 1 : -1);
        }

        return candidate == null ? 0 : candidate;
    }
}
