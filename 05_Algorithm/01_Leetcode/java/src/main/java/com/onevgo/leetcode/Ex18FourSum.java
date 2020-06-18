package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex18FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 参考 三数之和, 只是多了层循环
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        int len = nums.length;
        // sort nums
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 优化一: 如果最小的值都大于 target 直接break.
            //        如果最大的值都小于 target 跳过这轮
            if ((nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3]) > target) break;
            if ((nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1]) < target) continue;
            for (int j = i + 1; j < len - 2; j++) {
                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                // 优化二: 如果最小的值都大于 target continue.
                //        如果最大的值都小于 target 跳过这轮
                if ((nums[i] + nums[j] + nums[j + 1] + nums[j + 2]) > target) break;
                if ((nums[i] + nums[j] + nums[len - 2] + nums[len - 1]) < target) continue;

                int left = j + 1, right = len - 1;
                while (left < right) {
                    int res = nums[i] + nums[j] + nums[left] + nums[right];
                    if (res == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (res < target) left++;
                    else right--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
//        int[] ints = {1, 0, -1, 0, -2, 2};
//        int[] ints = {0, 0, 0, 0};
        //[1,-2,-5,-4,-3,3,3,5]
        //-11
        int[] ints = {1, -2, -5, -4, -3, 3, 3, 5};
        System.out.println(new Ex18FourSum().fourSum(ints, -11));
    }
}
