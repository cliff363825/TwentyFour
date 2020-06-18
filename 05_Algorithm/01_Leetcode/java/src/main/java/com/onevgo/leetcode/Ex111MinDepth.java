package com.onevgo.leetcode;

public class Ex111MinDepth {
    public int minDepth(TreeNode root) {
        return recursive(root, 0);
    }

    private int recursive(TreeNode root, int n) {
        if (root == null) return n;
        if (root.left == null && root.right == null) {
            return n + 1;
        }
        int leftNum = -1;
        if (root.left != null) {
            leftNum = recursive(root.left, n + 1);
        }
        int rightNum = -1;
        if (root.right != null) {
            rightNum = recursive(root.right, n + 1);
        }
        if (leftNum == -1) {
            return rightNum;
        } else if (rightNum == -1) {
            return leftNum;
        } else {
            return Math.min(leftNum, rightNum);
        }
    }

    public static void main(String[] args) {

    }
}
