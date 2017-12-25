package com.bryanrady.datastruct_alogrithm.leetcode.graph;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc542_01Matrix {

    /**
     * 图论05 - 0 1矩阵 - 中等 - 542

     给出一个由0和1组成的矩阵，找出矩阵中每个小格中，离它最近的0的距离。

     两个格子之间的距离为1。

     比如举两个例子：

     输入          输出
     0 0 0       0 0 0
     0 1 0       0 1 0
     0 0 0       0 0 0
     ------------------
     0 0 0       0 0 0
     0 1 0       0 1 0
     1 1 1       1 2 1

     注意：
     1. 矩阵的元素最多不超过10000个。
     2. 给定的矩阵中至少有一个0。
     3. 相邻格子只能是上下左右四个方向的。
     */

    public int[][] updateMatrix ( int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0) {
                    if (i > 0 && j > 0)
                        matrix[i][j] = Math.min(matrix[i][j - 1], matrix[i - 1][j]) + 1;
                    else if (i > 0)
                        matrix[i][j] = matrix[i - 1][j] + 1;
                    else if (j > 0)
                        matrix[i][j] = matrix[i][j - 1] + 1;
                    else
                        matrix[i][j] = row + col;
                }
            }
        }

        for (int i = row - 1; i > -1; i--) {
            for (int j = col - 1; j > -1; j--) {
                if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                    if (i < row - 1 && j < col - 1)
                        matrix[i][j] = Math.min(matrix[i][j], Math.min(matrix[i][j + 1], matrix[i + 1][j]) + 1);
                    else if (i < row - 1)
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                    else if (j < col - 1)
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                }
            }
        }

        return matrix;
    }

}
