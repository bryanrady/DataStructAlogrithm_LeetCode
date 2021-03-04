package com.bryanrady.leetcode.algorithms;

import java.util.HashMap;
import java.util.Map;

public class L1_TwoSum {

//    Example 1:
//
//    Input: nums = [2,7,11,15], target = 9
//    Output: [0,1]
//    Output: Because nums[0] + nums[1] == 9, we return [0, 1].
//    Example 2:
//
//    Input: nums = [3,2,4], target = 6
//    Output: [1,2]
//    Example 3:
//
//    Input: nums = [3,3], target = 6
//    Output: [0,1]

//    给定一个整数数组nums和一个整数目标，返回两个数字的索引，使它们相加到目标。
//    您可以假设每个输入正好有一个解决方案，并且不能两次使用同一个元素。
//    你可以按任何顺序返回答案。

    /***
     * 蛮力法  Brute Force 0ms
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            for (int j = i+1; j < nums.length; j++){
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalStateException("No Answer");
    }

    /**
     * Two-pass Hash Table 3ms
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        //将值作为键，值对应的数组下标作为值放进哈希表中
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        for (int j = 0; j < nums.length; j++){
            int complement = target - nums[j];
            if (map.containsKey(complement) && map.get(complement) != j){
                return new int[]{map.get(complement), j};
            }
        }

        throw new IllegalStateException("No Answer");
    }

    /**
     * One-pass Hash Table 0ms
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        //将值作为键，值对应的数组下标作为值放进哈希表中
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if (map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalStateException("No Answer");
    }

}
