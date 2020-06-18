package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex39CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //2,3,6,7
        //2 -> 2 -> 2
        //       -> 3
        //       -> 6
        //       -> 7
        //  -> 3
        //  -> 6
        //  -> 7
        // 回溯算法
        List<List<Integer>> lists = new ArrayList<>();
        backtrace(lists, new ArrayList<>(), candidates, target, 0);
        return lists;
    }

    public void backtrace(List<List<Integer>> lists, List<Integer> prev, int[] candidates, int target, int index) {
        if (target == 0) {
            lists.add(new ArrayList<>(prev));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }

            prev.add(candidates[i]);
            backtrace(lists, prev, candidates, target - candidates[i], i);
            prev.remove(prev.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Ex39CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
