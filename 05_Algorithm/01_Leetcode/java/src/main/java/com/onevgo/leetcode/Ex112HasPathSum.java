package com.onevgo.leetcode;

public class Ex112HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return recursive(root, sum);
    }

    private boolean recursive(TreeNode root, int sum) {
        sum -= root.val;
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return (root.left != null && recursive(root.left, sum)) ||
                (root.right != null && recursive(root.right, sum));
    }

    public static void main(String[] args) {

    }
}
