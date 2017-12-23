package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc213_HouseRobberII {

    /**
     * 动态规划08 - 入室行窃2 - 中等 - 213

     和198的题类似，只不过这次街道是环形的，数组中第一个房子和最后一个房子为相邻的。
     */

    public int rob(int[] nums) {
        if (nums == null)
            return 0;
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        return Math.max(robDP(nums, 0, n - 2), robDP(nums, 1, n - 1));
    }

    int robDP(int[] nums, int first, int last) {
        int n = last - first + 1;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[first];
        int dp[] = new int[n];
        dp[0] = nums[first];
        // 注意下标
        dp[1] = Math.max(nums[first], nums[first + 1]);
        for (int i = 2; i < n; i++)
            dp[i] = Math.max(dp[i - 2] + nums[first + i], dp[i - 1]);
        return dp[n - 1];

    }

}
