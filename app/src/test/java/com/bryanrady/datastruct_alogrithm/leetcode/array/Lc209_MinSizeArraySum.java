package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc209_MinSizeArraySum {

    /**
     * 数组10 - 没有重复字符的最长子串 - 中等 - 3
     * 【题中包含的数组的进阶技术：滑动窗口技术】
     给定一个整数和一个数组s，找出数组s中最短的一个连续子数组，使得连续子数组中的元素之和sum>=s。
     返回这个最短连续子数组。
     比如：
     nums = [2, 3, 1, 2, 4, 3], s = 7
     答案为[4, 3]
     */


    /**
     * 3ms
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 1)
            return 0;
        int head = 0;
        int tail = 0;
        int sum = 0;
        int result = nums.length;
        boolean exists = false;

        while(tail <= nums.length){
            if(sum >= s){
                exists = true;
                if(tail - head == 1)
                    return 1;
                result = Math.min(result, tail - head);
                sum -= nums[head];
                head++;
            }else{
                if(tail == nums.length)
                    break;
                sum += nums[tail];
                tail++;
            }
        }
        if(exists)
            return result;
        else
            return 0;
    }


    /**
     * leercode  2ms
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int i=0;
        int j=0;
        int sum=0;
        int result=nums.length+1;
        while(j<nums.length){
            sum+=nums[j++];
            while(sum>=s){
                result=Math.min(result,j-i);
                sum -= nums[i++];
            }
        }
        return result==nums.length+1?0:result;
    }
}
