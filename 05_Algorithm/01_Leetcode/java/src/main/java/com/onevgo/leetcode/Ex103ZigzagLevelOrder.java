package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ex103ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        recursive(res, root, 1);
        return res;
    }

    private void recursive(List<List<Integer>> res, TreeNode node, int n) {
        if (node == null) return;
        if (res.size() < n) {
            res.add(new LinkedList<>());
        }
        if (n % 2 == 1) {
            res.get(n - 1).add(node.val);
        } else {
            res.get(n - 1).add(0, node.val);
        }
        recursive(res, node.left, n + 1);
        recursive(res, node.right, n + 1);
    }

    public static void main(String[] args) {

    }
}
