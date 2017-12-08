package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/8.
 */

public class Lc80_RemoveDuplicatesFromSort2 {

    /**
     * 给定一个有序数组，原地删除重复元素使得数组中的元素最多重复2次，并且返回新长度。
     * 禁止申请额外空间，确保空间复杂度为O(1)。
     * 比如，给定nums = [1, 1, 1, 2, 2, 3],返回length = 5， nums = [1, 1, 2, 2, 3]。同样不用考虑超出新长度之后的空间遗留。
     */

    /**
     * 目前最优算法  1ms
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums){
        if(nums.length <= 2){
            return nums.length;
        }
        int index = 2;
        for(int i=2;i<nums.length;i++){
            if(nums[i] != nums[index-2]){
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    /**
     * 从自己想的  基本也是最优算法和上面的方法逻辑基本一样     2ms
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums){
        if(nums.length<=2){
            return nums.length;
        }
        int index = 2;
        for(int i=2;i<nums.length;i++){
            if(nums[i]!=nums[index-1]){
                nums[index++] = nums[i];
            }else if(nums[i]==nums[index-1] && nums[i-1]!=nums[index-2]){
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
