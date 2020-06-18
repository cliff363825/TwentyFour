package com.onevgo.leetcode;

public class Ex35SearchInsert {
    public int searchInsert(int[] nums, int target) {
        //输入: [1,3,5,6], 0
        //输出: 0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert1(int[] nums, int target) {
        // 二分查找
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) {

    }
}
