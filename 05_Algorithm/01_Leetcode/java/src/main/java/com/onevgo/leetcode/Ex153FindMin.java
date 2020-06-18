package com.onevgo.leetcode;

public class Ex153FindMin {
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums[0] < nums[nums.length - 1]) return nums[0];

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) return nums[mid+1];
            if (nums[mid] < nums[mid - 1]) return nums[mid];
            if (nums[mid] < nums[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
    public static void main(String[] args) {

    }
}
