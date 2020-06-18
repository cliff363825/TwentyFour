package com.onevgo.leetcode;

import java.util.*;

public class Ex145PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();

        if (root != null) deque.push(root);

        while (!deque.isEmpty()) {
            TreeNode current = deque.pop();
            list.addFirst(current.val);
            if (current.left != null) {
                deque.push(current.left);
            }
            if (current.right != null) {
                deque.push(current.right);
            }
        }

        return list;
    }

    public static void main(String[] args) {

    }
}
