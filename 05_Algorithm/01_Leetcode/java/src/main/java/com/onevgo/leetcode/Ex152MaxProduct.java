package com.onevgo.leetcode;

public class Ex152MaxProduct {
    public int maxProduct(int[] nums) {
        int max = nums[0], iMax = nums[0], iMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = iMin;
                iMin = iMax;
                iMax = tmp;
            }

            iMax = Math.max(iMax * nums[i], nums[i]);
            iMin = Math.min(iMin * nums[i], nums[i]);

            max = Math.max(max, iMax);
        }

        return max;
    }
}
