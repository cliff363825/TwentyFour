package com.onevgo.leetcode;

public class Ex114Flatten {
    public void flatten(TreeNode root) {
        TreeNode current = root;
        TreeNode node = null;
        while (current != null) {
            if (current.left == null) {
                current = current.right;
            } else {
                node = current.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = current.right;
                current.right = current.left;
                current.left = null;
                current = current.right;
            }
        }
    }

    public static void main(String[] args) {

    }
}
