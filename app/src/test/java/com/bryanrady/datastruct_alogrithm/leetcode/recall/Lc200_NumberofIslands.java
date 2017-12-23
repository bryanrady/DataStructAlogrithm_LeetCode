package com.bryanrady.datastruct_alogrithm.leetcode.recall;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc200_NumberofIslands {

    /**
     * 递归回溯06（floodfill算法） - 岛屿数量 - 中等 - 200

     给定一个二维数组，“1”表示陆地，“0”表示水。横向或纵向的陆地形成岛屿，被水域分开。问给出的地图中有多少个岛屿。

     比如:
     11110
     11010
     11000
     00000
     有1个岛屿，

     11000
     11000
     00100
     00011

     有3个岛屿。
     */

    private int n;
    private int m;

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
}
