package com.onevgo.leetcode;

public class Ex41FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        final int ONE = 1;
        if (nums == null || nums.length == 0) {
            return ONE;
        }

        // check 1 exists?
        boolean num1Exists = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ONE) {
                num1Exists = true;
                break;
            }
        }
        if (!num1Exists) {
            return ONE;
        }
        if (nums.length == 1) {
            return ONE + 1; // 2
        }

        // negative, 0 -> 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }

        // 不妨令 nums[n] 表示 正整数 n 是否存在
        // 当 nums[n] 为正数，则说明n不存在。为负数则说明存在
        // 例如，nums[1]=4,说明1不存在...
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (index == nums.length) {
                nums[0] = Math.abs(nums[0]) * -1;
            } else {
                nums[index] = Math.abs(nums[index]) * -1;
            }
        }

        // [1..n-1]逐一判断是否存在
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        // [n]是否存在
        if (nums[0] > 0) {
            return nums.length;
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {

    }
}
