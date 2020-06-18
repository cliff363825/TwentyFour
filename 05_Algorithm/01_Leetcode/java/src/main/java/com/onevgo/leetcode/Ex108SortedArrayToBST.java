package com.onevgo.leetcode;

public class Ex108SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursive(nums, 0, nums.length);
    }

    private TreeNode recursive(int[] nums, int start, int end) {
        // nums[start:end]
        if (start >= end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = recursive(nums, start, mid);
        node.right = recursive(nums, mid + 1, end);
        return node;
    }

    public static void main(String[] args) {

    }
}
