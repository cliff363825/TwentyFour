package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Ex102LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        recursive(res, root, 1);
        return res;
    }

    private void recursive(List<List<Integer>> res, TreeNode node, int n) {
        if (node == null) return;
        if (res.size() < n) {
            ArrayList<Integer> row = new ArrayList<>();
            res.add(row);
        }
        res.get(n - 1).add(node.val);
        recursive(res, node.left, n + 1);
        recursive(res, node.right, n + 1);
    }

    public static void main(String[] args) {

    }
}
