package com.onevgo.leetcode;

public class Ex105BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return recursive(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    private TreeNode recursive(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft >= preRight || inLeft >= inRight) {
            return null;
        }
        // root val
        TreeNode root = new TreeNode(preorder[preLeft]);
        int current = inLeft;
        while (inorder[current] != root.val) {
            current++;
        }
        int leftTreeNum = current - inLeft;
        // left tree: preorder[preLeft+1:preLeft+1+leftTreeNum]
        //            inorder[inLeft:current]
        root.left = recursive(preorder, inorder, preLeft + 1, preLeft + 1 + leftTreeNum, inLeft, current);
        // right tree: preorder[preLeft+1+leftTreeNum:preRight]
        //             inorder[current+1:inRight]
        root.right = recursive(preorder, inorder, preLeft + 1 + leftTreeNum, preRight, current + 1, inRight);
        return root;
    }

    public static void main(String[] args) {

    }
}
