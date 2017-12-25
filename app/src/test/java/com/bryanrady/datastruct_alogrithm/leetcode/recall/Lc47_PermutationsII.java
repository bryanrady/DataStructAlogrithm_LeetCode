package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc47_PermutationsII {

    /**
     * 递归回溯02（排列问题）- 排列2 - 中等 - 47
     给定一个整形数组，其中可能有相同的元素，返回这些元素所有排列的可能（不重复）。
     比如给出[1,1,2]，则结果为：
     [
     [1,1,2],
     [1,2,1],
     [2,1,1]
     ]
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length == 0){
            return null;
        }
        Arrays.sort(nums);
        getResult(result,nums,new ArrayList<Integer>(),0,new int[nums.length]);
        return result;
    }

    private void getResult(List<List<Integer>> result,int[] nums,List<Integer> ans,int num,int[] pos){
        if( num == nums.length){
            result.add(new ArrayList<Integer>(ans));
            return;
        }
        for( int i = 0 ; i<nums.length;i++){
            if( pos[i] == 0 ){
                ans.add(nums[i]);
                pos[i] = 1;
                getResult(result,nums,ans,num+1,pos);
                pos[i] = 0;
                ans.remove(num);
                while(i<nums.length-1 && nums[i] == nums[i+1]){//在这里判断之后的数字是否一样，如果一样，就直接跳过。
                    i++;
                }
            }
        }
    }
}
