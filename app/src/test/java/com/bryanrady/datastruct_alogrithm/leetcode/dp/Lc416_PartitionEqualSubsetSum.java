package com.bryanrady.datastruct_alogrithm.leetcode.dp;

import java.util.Arrays;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc416_PartitionEqualSubsetSum {

    /**
     * 动态规划11(背包问题变种) - 分割子集和相等 - 中等 - 416

     给定一个非空数组，其中所有的数字都是正整数。问是否可以将这个数组的元素分成两部分，使得每部分的数字和相等？

     如对[1,5,11,5]，可以分成[1,5,5]和[11]两部分，元素和相等，返回true。
     如对[1,2,3,5]，无法分成元素和相等的两部分，返回false。
     */

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        reverse(nums);
        Arrays.sort(nums);
        reverse(nums);
        return dfs(nums, sum / 2, 0, 0);
    }

    public boolean dfs(int[] nums, int target, int sum, int index) {
        if (sum == target) {
            return true;
        }
        for (int i = index; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                break;
            }
            if (dfs(nums, target, sum + nums[i], i + 1)) {
                return true;
            }
        }
        return false;
    }

    public void reverse(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            nums[i] *= -1;
        }
    }
}
