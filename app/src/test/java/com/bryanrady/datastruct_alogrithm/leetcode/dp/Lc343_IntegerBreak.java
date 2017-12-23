package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc343_IntegerBreak {

    /**
     *  动态规划04 - 分解整数 - 中等 - 343

     给定一个正整数n，将它分解成至少两个正整数，并且使得分解出的正整数的乘积最大。
     算法返回这个最大乘积。

     比如给定n=2，返回1（2=1+1）。给定n=10，返回36（10=3+3+4）。
     可以假设n大于等于2且小于58。
     */

    public int integerBreak(int n) {
        if (n <= 1) {
            return 0;
        }
        int[] dp = new int[n+1];
        if (n >= 2) {
            dp[2] = 1;
        }
        if (n >= 3) {
            dp[3] = 2;
        }
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(2 * Math.max(dp[i-2], i-2), 3 * Math.max(dp[i-3], i-3));
        }
        return dp[n];
    }
}
