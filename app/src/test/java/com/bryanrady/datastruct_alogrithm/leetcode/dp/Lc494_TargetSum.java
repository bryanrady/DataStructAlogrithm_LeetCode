package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc494_TargetSum {

    /**
     * 动态规划13(背包问题变种) - 目标和 - 中等 - 494

     给定一个非0数字序列，在这些数字前加上+或者-的符号，使其计算结果为给定的整数S。
     问一共有多少种可能。

     如：nums = [1,1,1,1,1]，S=3
     答案为5。

     -1+1+1+1+1 = 3
     +1-1+1+1+1 = 3
     +1+1-1+1+1 = 3
     +1+1+1-1+1 = 3
     +1+1+1+1-1 = 3
     */


    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
    }

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }
}
