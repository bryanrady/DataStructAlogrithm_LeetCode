package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc64_MinimumPathSum {

    /**
     * 动态规划03 - 矩阵最小路径 - 中等 - 64

     给定一个m x n 的矩阵，矩阵内元素为非负数，找到一条自左上到右下的路径，使得经过的和最小。

     注意：
     你只能选择向下或者向右移动。

     比如：
     [[1,3,1],
     [1,5,1],
     [4,2,1]]

     返回结果为7，路径是1->3->1->1。
     */

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 1; i < m; i++){
            grid[i][0] += grid[i-1][0];
        }
        for(int i = 1; i < n; i++){
            grid[0][i] +=grid[0][i-1];
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }
}
