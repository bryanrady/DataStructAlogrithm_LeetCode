package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/8.
 */

public class Lc167_TwoSumTarget {

    /**
     * 给定一个整形数组，并且数组内元素已经按升序排列，找出两个元素，使得它们之和与给定的数相等。
     * 函数应该返回找到的这两个元素的索引，并且第一个元素的索引小于等于第二个元素的索引，并且元素索引起始位置是基于1而不是基于0。
     * 你可以假设给定的目标数在数组中必定找得到对应的两个元素。
     * 比如：输入： numbers = [2, 7, 11, 15], target = 9
     *       输出： index = 1, index = 2
     */

    /**
     * 基本是最优解法 2ms
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers,int target){
        int low = 0;
        int high = numbers.length-1;
        while (low < high){
            if(numbers[low]+numbers[high] == target){
                return new int[]{low+1,high+1};
            }else if (numbers[low]+numbers[high] > target){
                high--;
            }else {
                low++;
            }
        }
        return null;
    }
}
