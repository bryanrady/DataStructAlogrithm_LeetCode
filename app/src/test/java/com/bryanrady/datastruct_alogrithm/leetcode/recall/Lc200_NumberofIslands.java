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

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ++count;
                }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
