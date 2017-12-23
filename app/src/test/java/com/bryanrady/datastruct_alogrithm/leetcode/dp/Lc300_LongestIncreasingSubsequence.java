package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc300_LongestIncreasingSubsequence {

    /**
     * 动态规划14（LIS问题） - 最长上升子序列 - 中等 - 300

     给定一个整数序列，求其中的最长上升子序列的长度。

     如[10, 9, 2, 5, 3, 7, 101, 18]，期最长上升子序列的长度为4。

     最长上升子序列为[2,5,7,101]
     */

    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] record = new int[nums.length + 1];
        record[0] = nums[0];
        int len = 1;
        for(int i = 1; i < nums.length; i++){
            int crt = nums[i];
            if(crt <= record[0]){
                record[0] = crt;
            }else if(crt > record[len - 1]){
                record[len] = crt;
                len++;
            }else{
                insertNum(record, len, crt);
            }
        }
        return len;
    }

    private void insertNum(int[] record, int len, int crt){
        int start = 0, end = len - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(record[mid] == crt){
                return;
            }else if(record[mid] < crt){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(record[start] >= crt){
            record[start] = crt;
        }else{
            record[end] = crt;
        }
    }
}
