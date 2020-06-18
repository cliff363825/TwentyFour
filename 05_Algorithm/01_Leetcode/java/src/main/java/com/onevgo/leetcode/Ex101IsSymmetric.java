package com.onevgo.leetcode;

public class Ex101IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recursive(root.left, root.right);
    }

    private boolean recursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return recursive(left.left, right.right) && recursive(left.right, right.left);
    }

    public static void main(String[] args) {

    }
}
