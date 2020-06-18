package com.onevgo.leetcode;

public class Ex79Exist {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // first char
                if (word.charAt(0) == board[i][j]) {
                    boolean[][] seen = new boolean[board.length][board[i].length];
                    if (backtrace(board, word, i, j, 0, seen)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrace(char[][] board, String word, int i, int j, int n, boolean[][] seen) {
        if (word.length() == n) {
            // complete
            return true;
        }
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[i].length - 1 ||
                board[i][j] != word.charAt(n) || seen[i][j]
        ) {
            return false;
        }

        // mark
        seen[i][j] = true;
        boolean res = backtrace(board, word, i - 1, j, n + 1, seen) ||
                backtrace(board, word, i, j - 1, n + 1, seen) ||
                backtrace(board, word, i + 1, j, n + 1, seen) ||
                backtrace(board, word, i, j + 1, n + 1, seen);
        // mark clear
        seen[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        char[][] chars = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        System.out.println(new Ex79Exist().exist(chars, "ABCCED"));
    }
}
