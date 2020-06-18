package com.onevgo.leetcode;

public class Ex129SumNumbers {
    public int sumNumbers(TreeNode root) {
        return recursive(root, 0);
    }

    private int recursive(TreeNode root, int prev) {
        if (root == null) return 0;
        int current = prev * 10 + root.val;
        if (root.left == null && root.right == null) {
            return current;
        }
        return recursive(root.left, current) + recursive(root.right, current);
    }

    public static void main(String[] args) {

    }
}
