package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc51_N_Queens {

    /**
     * 递归回溯07 - n皇后问题 - 困难 - 51

     求n皇后问题的所有解。

     n个皇后摆放在n*n的棋盘格中，使得横竖和两个对角线方向均不会同时出现两个皇后。

     比如下面有两个4皇后问题的解：

     [
     [".Q..",  // 解1
     "...Q",
     "Q...",
     "..Q."],

     ["..Q.",  // 解2
     "Q...",
     "...Q",
     ".Q.."]
     ]
     */


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        //先在二维数组中填'.'
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = '.';
            }
        }

        eightQueen(list, map, 0);
        return list;
    }

    private void eightQueen(List<List<String>> list, char[][] map, int row) {
        if (row == map.length) {   //退出递归的条件
            list.add(construct(map));
            return;
        }
        for (int i = 0; i < map.length; i++) {
            if (judge(map, row, i)) {
                continue;
            }
            map[row][i] = 'Q';
            eightQueen(list, map, row + 1);
            map[row][i] = '.';
        }
    }

    private boolean judge(char[][] map, int row, int col) {
        for (int i = 0; i < row; i++) {
            int j = 0;
            while (j < map.length && map[i][j] != 'Q') {
                j++;
            }
            if (j == col || Math.abs(col - j) == Math.abs(row - i)) {
                return true;
            }
        }
        return false;
    }

    private List<String> construct(char[][] map) {
        List<String> list = new ArrayList<>();
        for(char[] c : map){
            String s = new String(c);
            list.add(s);
        }
        return list;
    }

}
