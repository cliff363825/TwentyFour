package com.onevgo.leetcode;

public class Ex45Jump {
    public int jump(int[] nums) {
        int[] memo = new int[nums.length + 1];
        //   2, 3, 1, 1, 4
        // 0 0  1  1  2  2
        // 双指针法
        int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            int step = memo[i + 1];
            while (j < i + nums[i] && j < nums.length - 1) {
                j++;
                memo[j + 1] = step + 1;
            }
            i++;
        }
        return memo[nums.length];
    }

    public static void main(String[] args) {
        // 0 1 1 2 2
//        System.out.println(new Ex45Jump().jump(new int[]{2, 3, 1, 1, 4}));
//        System.out.println(new Ex45Jump().jump(new int[]{2, 3, 0, 1, 4}));
//        System.out.println(new Ex45Jump().jump(new int[]{1, 2, 3}));
        System.out.println(new Ex45Jump().jump(new int[]{5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5}));
    }
}
