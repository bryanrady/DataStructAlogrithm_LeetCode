package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/8.
 */

public class Lc26_RemoveDuplicatesFromSort {

    /**
     * 从有序数组中删除重复元素
     * 给定一个有序数组，原地删除重复元素使得数组中的元素只保留一个，并且返回新长度。
     * 禁止申请额外空间，确保空间复杂度为O(1)。
     *
     * 比如：
     * 给定nums = [1, 1, 2]，你的函数应该返回length = 2，nums = [1, 2]。
     * 不用考虑超出新长度之后的空间遗留。
     */

    /**
     * 自己想的解法 13ms 击败58%，Leetcode上基本也是这个解法
     * @param nums
     * @return
     */
    public static int removeDulicates(int[] nums){
        if(nums.length <= 1){
            return nums.length;
        }
        int index = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i] != nums[i-1]){
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
