package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex40CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 回溯算法
        Arrays.sort(candidates);
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
                break;
            }
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            prev.add(candidates[i]);
            backtrace(lists, prev, candidates, target - candidates[i], i + 1);
            prev.remove(prev.size() - 1);
        }
    }

    public static void main(String[] args) {

    }
}
