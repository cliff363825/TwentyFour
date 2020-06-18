package com.onevgo.leetcode;

public class Ex55CanJump {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Ex55CanJump().canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new Ex55CanJump().canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(new Ex55CanJump().canJump(new int[]{0, 2, 3}));
    }
}
