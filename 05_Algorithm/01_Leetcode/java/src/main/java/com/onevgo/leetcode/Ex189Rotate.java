package com.onevgo.leetcode;

public class Ex189Rotate {
    public void rotate(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = i;
            int next = (start + k) % nums.length;
            while (start != next) {
                count++;
                int temp = nums[next];
                nums[next] = nums[i];
                nums[i] = temp;
                start = next;
                next = (start + k) % nums.length;
                if (start == i) {
                    break;
                }
            }
            if (count == nums.length) {
                break;
            }
        }
    }
}
