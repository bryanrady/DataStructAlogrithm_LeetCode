package com.bryanrady.datastruct_alogrithm.leetcode.recall;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc37_SudokuSolver {

    /**
     * 递归回溯08 - 求解数独 - 困难 - 37

     设计一个算法求解数独。

     空的格子用"."表示。可以假设每个给定的题目只有一个解。
     */

    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }

    private boolean solveSudoku(char[][] board, int i, int j) {
        if (i == 9) {
            return true;
        }
        if (j == 9) {
            return solveSudoku(board, i + 1, 0);
        }
        if (board[i][j] == '.') {
            for (char k = '1'; k <= '9'; k++) {
                board[i][j] = k;
                if (judge(board, i, j)) {
                    if (solveSudoku(board, i, j + 1)) {
                        return true;
                    }
                }
                board[i][j] = '.';
            }
        } else {
            return solveSudoku(board, i, j + 1);
        }
        return false;
    }

    private boolean judge(char[][] board, int i, int j) {
        char temp = board[i][j];
        for (int k = 0; k < 9; k++) {
            if (k != i && board[k][j] == temp) {
                return false;
            }
            if (k != j && board[i][k] == temp) {
                return false;
            }
        }
        for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
            for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {
                if (row != i && col != j && board[row][col] == temp) {
                    return false;
                }
            }
        }
        return true;
    }
}
