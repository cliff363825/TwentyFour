package com.onevgo.leetcode;

public class Ex98IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return recursive(root, null, null);
    }

    private boolean recursive(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!recursive(node.right, val, upper)) return false;
        if (!recursive(node.left, lower, val)) return false;
        return true;
    }

    public static void main(String[] args) {

    }
}
