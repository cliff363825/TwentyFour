package com.onevgo.leetcode;

import java.util.Arrays;

public class Ex59GenerateMatrix {
    //给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
    //
    //示例:
    //输入: 3
    //输出:
    //[
    // [ 1, 2, 3 ],
    // [ 8, 9, 4 ],
    // [ 7, 6, 5 ]
    //]
    public int[][] generateMatrix(int n) {
        int size = n * n; // 9
        int[][] res = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        //         右  下  左 上
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0, y = 0, direct = 0;
        int i = 1;
        while (i <= size) {
            res[x][y] = i;
            seen[x][y] = true;
            int nextX = x + dx[direct];
            int nextY = y + dy[direct];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !seen[nextX][nextY]) {
                // pass
            } else {
                // change direct
                direct = (direct + 1) % 4;
                nextX = x + dx[direct];
                nextY = y + dy[direct];
            }
            x = nextX;
            y = nextY;
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Ex59GenerateMatrix().generateMatrix(3)));
    }
}
