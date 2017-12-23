package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc279_PerfectSquares {

    /**
     * 动态规划05 - 完全平方数 - 中等 -279

     给出一个正整数n，寻找最少的完全平方数，使得它们的和为n。

     比如：
     12 = 4+4+4
     13 = 4 + 9
     */

    public int numSquares(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) {
            // Use local variable.
            // Don't use nums[i]. It will cause cache miss and reduce the performance.
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, 1 + nums[i - j * j]);
            }
            nums[i] = min;
        }
        return nums[n];
    }
}
