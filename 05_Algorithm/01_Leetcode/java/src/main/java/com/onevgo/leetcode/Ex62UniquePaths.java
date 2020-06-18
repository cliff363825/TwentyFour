package com.onevgo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex62UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        int[][] memo = new int[m][n];
        // init
        Arrays.fill(memo[0], 1);
        for (int i = 0; i < m; i++) {
            memo[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }

    private void backtrace(List<List<String>> list, int m, int n, List<String> path, int x, int y) {
        if (x == n - 1 && y == m - 1) {
            list.add(new ArrayList<>(path));
            return;
        }

        if (x < n) {
            path.add("右");
            backtrace(list, m, n, path, x + 1, y);
            path.remove(path.size() - 1);
        }
        if (y < m) {
            path.add("下");
            backtrace(list, m, n, path, x, y + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Ex62UniquePaths().uniquePaths(3, 2));
    }
}
