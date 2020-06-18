package com.onevgo.leetcode;

public class Ex104MaxDepth {
    public int maxDepth(TreeNode root) {
        return recursive(root, 0);
    }

    private int recursive(TreeNode node, int n) {
        if (node == null) return n;
        return Math.max(
                recursive(node.left, n + 1),
                recursive(node.right, n + 1)
        );
    }
}
