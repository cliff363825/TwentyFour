package com.onevgo.leetcode;

public class Ex100IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return recursive(p, q);
    }

    private boolean recursive(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return recursive(p.left, q.left) && recursive(p.right, q.right);
    }

    public static void main(String[] args) {

    }
}
