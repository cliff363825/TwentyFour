package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex16ThreeSumClosest {
    //给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
    //
    //例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
    //
    //与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
    public int threeSumClosest(int[] nums, int target) {
        // 排序+双指针
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return ans;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //[0,2,1,-3]
        // [-3,0,1,2]
        //1
        System.out.println(new Ex16ThreeSumClosest().threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }
}
