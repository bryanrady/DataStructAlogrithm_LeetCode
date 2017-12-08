package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/8.
 */

public class Lc27_RemoveElement {

    /**
     * 删除元素
     * 给定一个数组和一个值，原地移除数组中所有给定的值，并返回新数组的长度。
     * 不允许申请额外空间，确保空间复杂度为O(1)。
     * 数组中的元素可以被改变，不用考虑超出新长度之后的空间遗留。
     * 比如：
     * 给定nums = [3, 2, 2 ,3]， val = 3，你的函数应该返回length = 2， nums = [2, 2]。
     */

    /**
     * 目前来看我这个是最优解法 8ms     击败88%
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums,int val){
        int k = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != val){
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
