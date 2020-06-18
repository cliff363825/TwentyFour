package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex75SortColors {
    public void sortColors(int[] nums) {
        // A[0..i-1] = 0
        int i = 0;
        // A[j+1..end] = 2
        int j = nums.length - 1;
        int k = 0;
        while (k <= j) {
            if (nums[k] == 0) {
                // swap A[i],A[k]
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
                i++;
            } else if (nums[k] == 2) {
                // swap A[j],A[k]
                int tmp = nums[j];
                nums[j] = nums[k];
                nums[k] = tmp;
                j--;
                continue;
            }
            k++;
        }
    }

    public static void main(String[] args) {
        int[] ints = {2, 0, 2, 1, 1, 0};
        new Ex75SortColors().sortColors(ints);
        System.out.println(Arrays.toString(ints));
    }
}
