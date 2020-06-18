package com.onevgo.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Ex94InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode current = root;

        while (current != null || !deque.isEmpty()) {
            // left
            while (current != null) {
                deque.push(current);
                current = current.left;
            }
            current = deque.pop();
            // value
            ans.add(current.val);
            // right
            current = current.right;
        }
        return ans;
    }

    // 莫里斯中序遍历
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> ldr = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {//左子树为空，输出当前节点，将其右孩子作为当前节点
                ldr.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;//左子树
                while (pre.right != null && pre.right != cur) {//找到前驱节点，即左子树中的最右节点
                    pre = pre.right;
                }
                //如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                //如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
                if (pre.right == cur) {
                    pre.right = null;
                    ldr.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return ldr;
    }

    public static void main(String[] args) {

    }
}
