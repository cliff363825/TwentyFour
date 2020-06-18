package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex90SubsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<>();
        backtrace(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtrace(List<List<Integer>> res, List<Integer> prev, int[] nums, int i) {
        res.add(new ArrayList<>(prev));

        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            prev.add(nums[j]);
            backtrace(res, prev, nums, j + 1);
            prev.remove(prev.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 2};
        System.out.println(new Ex90SubsetsWithDup().subsetsWithDup(ints));
    }
}
