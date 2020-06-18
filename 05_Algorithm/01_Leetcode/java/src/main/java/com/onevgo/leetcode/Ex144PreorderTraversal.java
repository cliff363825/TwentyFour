package com.onevgo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Ex144PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();

        if (root != null) deque.push(root);

        while (!deque.isEmpty()) {
            TreeNode current = deque.pop();
            list.add(current.val);
            if (current.right != null) {
                deque.push(current.right);
            }
            if (current.left != null) {
                deque.push(current.left);
            }
        }
        return list;
    }
}
