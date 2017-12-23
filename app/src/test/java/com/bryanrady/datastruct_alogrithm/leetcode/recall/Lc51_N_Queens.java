package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.ArrayList;
import java.util.Arrays;
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
        boolean[]
                //ocp0 = new boolean[n],
                ocp90 = new boolean[n],
                // 总共 2n - 1 种可能性，(0,0) 的 index 是 n - 1
                ocp45 = new boolean[2 * n - 1],
                ocp135 = new boolean[2 * n - 1];
        List<List<String>> ans = new ArrayList<List<String>>();
        char[][] map = new char[n][n];
        for (char[] tmp : map) Arrays.fill(tmp, '.'); //init

        solve(0, n, map, ans, ocp45, ocp90, ocp135);
        return ans;
    }

    private void solve(int depth, int n, char[][] map, List<List<String>> ans,
                       boolean[] ocp45, boolean[] ocp90, boolean[] ocp135) {
        if (depth == n) {
            addSolution(ans, map);
            return;
        }

        for (int j = 0; j < n; j++)
            // 每次都进行新的一行，所以行不用检查
            // 90度代表棋盘的列
            // 45度对角线（从左上到右下）同一条线上 row + col 是相等的，因此可用作 index
            // 135度对角线（从左下到右上）可从 (0,0) 即 index (n - 1) 为起点
            // 因为同一条对角线 row - col 值恒定，可用作 offset 表示对角线的 index.
            if (!ocp90[j] && !ocp45[depth + j] && !ocp135[j - depth + n - 1]) {
                ocp90[j] = true;
                ocp45[depth + j] = true;
                ocp135[j - depth + n - 1] = true;
                map[depth][j] = 'Q';

                solve(depth + 1, n, map, ans, ocp45, ocp90, ocp135);

                ocp90[j] = false;
                ocp45[depth + j] = false;
                ocp135[j - depth + n - 1] = false;
                map[depth][j] = '.';
            }
    }

    private void addSolution(List<List<String>> ans, char[][] map) {
        List<String> cur = new ArrayList<>();
        for (char[] i : map) cur.add(String.valueOf(i));
        ans.add(cur);
    }
}
