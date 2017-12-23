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

    public int[][] updateMatrix(int[][] matrix) {
        int r=matrix.length,c=matrix[0].length;
        int[][] dp=matrix;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(matrix[i][j]!=0){
                    if(i>0 && j>0)
                        //have to add this condition "Math.min(Integer.MAX_VALUE-1" otherwise it will be more than max value and will become negative at the end. previously it was just Math.min(dp[i][j-1],dp[i-1][j])+1).
                        dp[i][j]=Math.min(dp[i][j-1],dp[i-1][j])+1;
                    else if(i>0)
                        dp[i][j]=dp[i-1][j]+1;
                    else if(j>0)
                        dp[i][j]=dp[i][j-1]+1;
                    else
                        dp[i][j]=r+c;   //have to put max-1,since If cell below it add 1 in it, it will still be valid
                }
            }
        }
        for(int i=r-1;i>-1;i--){
            for(int j=c-1;j>-1;j--){
                if(dp[i][j] != 0 && dp[i][j] != 1){
                    if(i<r-1 && j<c-1)
                        dp[i][j]=Math.min(dp[i][j],Math.min(dp[i][j+1],dp[i+1][j])+1);
                    else if(i<r-1)
                        dp[i][j]=Math.min(dp[i][j],dp[i+1][j]+1);
                    else if(j<c-1)
                        dp[i][j]=Math.min(dp[i][j],dp[i][j+1]+1);
                }
            }
        }
        return dp;
    }
}
