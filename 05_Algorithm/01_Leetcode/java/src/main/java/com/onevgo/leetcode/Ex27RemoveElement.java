package com.onevgo.leetcode;

public class Ex27RemoveElement {
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        System.out.println(new Ex27RemoveElement().removeElement(new int[]{1,1}, 1));
        System.out.println(new Ex27RemoveElement().removeElement(new int[]{3,2,2,3}, 3));
    }
}
