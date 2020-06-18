package com.onevgo.leetcode;

public class Ex74SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int size = row * col;
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2; //0+11->5
            int item = matrix[mid / col][mid % col];
            if (item == target) {
                return true;
            } else if (item < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] ints = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50},
        };
        System.out.println(new Ex74SearchMatrix().searchMatrix(ints, 74));
    }
}
