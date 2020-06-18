package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex107LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        recursive(res, root, 1);
        Collections.reverse(res);
        return res;
    }

    private void recursive(List<List<Integer>> res, TreeNode root, int i) {
        if (res.size() < i) {
            res.add(new ArrayList<>());
        }
        res.get(i - 1).add(root.val);
        if (root.left != null) recursive(res, root.left, i + 1);
        if (root.right != null) recursive(res, root.right, i + 1);
    }
}
