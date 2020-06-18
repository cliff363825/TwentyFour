package com.onevgo.leetcode;

public class Ex26RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        // 双指针法
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }

    public static void main(String[] args) {

    }
}
