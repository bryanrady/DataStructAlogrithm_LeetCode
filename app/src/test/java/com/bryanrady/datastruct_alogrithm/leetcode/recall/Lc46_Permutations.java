package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc46_Permutations {

    /**
     * 递归回溯02（排列问题） - 排列 - 中等 - 46

     给定一个数字素组，返回它们所有可能的排列。

     比如：
     [1,2,3]，则结果为：
     [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
     ]
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        DFSHelper(result, nums, 0);
        return result;
    }

    private void DFSHelper(List<List<Integer>> result, int[] nums, int index){
        if(index == nums.length){
            List<Integer> path = new ArrayList<>();
            for(int i = 0; i < nums.length; i++){
                path.add(nums[i]);
            }
            result.add(path);
            return;
        }
        for(int i = index; i < nums.length; i++){
            swap(nums, index, i);
            DFSHelper(result, nums, index + 1);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
