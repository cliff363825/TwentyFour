package com.onevgo.leetcode;

public class Ex106BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return recursive(inorder, postorder,
                0, inorder.length,
                0, postorder.length);
    }

    private TreeNode recursive(int[] inorder, int[] postorder,
                               int inStart, int inEnd,
                               int postStart, int postEnd) {
        if (inStart >= inEnd || postStart >= postEnd) return null;
        // inorder: left -> root -> right
        // postorder: left -> right -> root
        // root val
        TreeNode root = new TreeNode(postorder[postEnd - 1]);
        int inCurrent = inStart;
        while (inorder[inCurrent] != root.val) {
            inCurrent++;
        }
        int leftTreeNum = inCurrent - inStart;
        int rightTreeNum = inEnd - inCurrent - 1;
        root.left = recursive(inorder, postorder, inStart, inCurrent, postStart, postStart + leftTreeNum);
        root.right = recursive(inorder, postorder, inCurrent + 1, inEnd, postStart + leftTreeNum, postStart + leftTreeNum + rightTreeNum);
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Ex106BuildTree().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    }
}
