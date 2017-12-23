package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.HashSet;

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
        HashSet[] hashset = new HashSet[27];
        for (int i = 0; i < 27; i++)
            hashset[i] = new HashSet<Character>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char Char = board[i][j];
                if (Char != '.') {
                    hashset[i].add(Char);
                    hashset[9 + j].add(Char);
                    hashset[18 + (i / 3) * 3 + j / 3].add(Char);
                }
            }
        }
        int flag = 0;
        char[][][] num = null ;
        while ( flag == 0) {
            flag = 1;
            num = new char[9][9][9];
            for (int i = 0; i < 9; i++) {// i代表第i个hashset
                for (int j = 1; j < 10; j++) {// j代表1-9
                    char ch = (char) (j + '0');
                    int[] test = new int[2];
                    if (!hashset[i].contains(ch)) {
                        test[0] = 0;
                        for (int k = 0; k < 9; k++) {
                            char Ch = board[i][k];
                            if (Ch == '.') {
                                if (!hashset[9 + k].contains(ch) && !hashset[18 + (i / 3) * 3 + k / 3].contains(ch)) {
                                    addNum(num, i, k, ch);
                                    test[0]++;
                                    test[1] = k;
                                }
                            }
                        }
                    }
                    if (test[0] == 1) {
                        board[i][test[1]] = ch;
                        hashset[i].add(ch);
                        flag = 0;
                        hashset[9 + test[1]].add(ch);
                        hashset[18 + (i / 3) * 3 + test[1] / 3].add(ch);
                    }

                }
            }

            for (int qq = 0; qq < 9 && flag == 1; qq++) {
                for (int j = 0; j < 9 && flag == 1; j++) {
                    if (getlen(num[qq][j]) == 1) {
                        char ch = num[qq][j][0];
                        board[qq][j] = ch;
                        flag = 0;
                        hashset[qq].add(ch);
                        hashset[9 + j].add(ch);
                        hashset[18 + (qq / 3) * 3 + j / 3].add(ch);
                    }

                }
            }

        }
        solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9 for each cell
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int i, int j, char c){
        for(int row = 0; row < 9; row++)
            if(board[row][j] == c)
                return false;

        for(int col = 0; col < 9; col++)
            if(board[i][col] == c)
                return false;

        for(int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
            for(int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if(board[row][col] == c)
                    return false;
        return true;
    }
    public static int getlen(char[] num) {
        int len = 0;
        for (int i = 0; i < 9; i++) {
            if (num[i] < '1' || num[i] > '9') {
                return len;
            } else
                len++;
        }
        return len;
    }

    public static void addNum(char[][][] num, int num1, int num2, char ch) {
        for (int i = 0; i < 9; i++) {
            if (num[num1][num2][i] < '0' || num[num1][num2][i] > '9') {
                num[num1][num2][i] = ch;
                break;
            }

        }

    }
}
