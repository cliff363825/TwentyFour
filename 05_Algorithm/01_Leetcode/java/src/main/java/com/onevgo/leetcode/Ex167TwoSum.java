package com.onevgo.leetcode;

public class Ex167TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                return new int[]{i, j};
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
