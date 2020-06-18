package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex113PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        recursive(res, new ArrayList<>(), root, sum);
        return res;
    }

    private void recursive(List<List<Integer>> res, List<Integer> prev, TreeNode root, int sum) {
        sum -= root.val;
        prev.add(root.val);
        if (root.left == null && root.right == null) {
            // leaf node
            if (sum == 0) {
                res.add(new ArrayList<>(prev));
            }
            prev.remove(prev.size() - 1);
            return;
        }
        if (root.left != null) {
            recursive(res, prev, root.left, sum);
        }
        if (root.right != null) {
            recursive(res, prev, root.right, sum);
        }
        prev.remove(prev.size() - 1);
    }

    public static void main(String[] args) {

    }
}
