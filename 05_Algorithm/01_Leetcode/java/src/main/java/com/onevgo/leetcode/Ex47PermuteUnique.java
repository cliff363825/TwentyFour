package com.onevgo.leetcode;

import java.util.*;

public class Ex47PermuteUnique {

    public void backtrack(List<List<Integer>> output,
                          List<Integer> item,
                          int[] nums,
                          boolean[] used) {
        // if all integers are used up
        if (item.size() == nums.length) {
            output.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) { // 0 1 2
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])) {
                continue;
            }
            used[i] = true;
            item.add(nums[i]); // 1 1 2 // 1 2
//            System.out.println(item);
            backtrack(output, item, nums, used);//
            item.remove(item.size() - 1); // 1
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> output = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(output, new ArrayList<>(), nums, used);
        return output;
    }

    public static void main(String[] args) {
//        System.out.println(new Ex47PermuteUnique().permuteUnique(new int[]{1, 1, 2}));
        System.out.println(new Ex47PermuteUnique().permuteUnique(new int[]{2, 2, 1, 1}));
    }
}
