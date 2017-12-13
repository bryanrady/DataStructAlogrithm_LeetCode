package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc75_SortColors {

    /**
     * 【题中包含的数组的进阶技术：对撞指针、三路快速排序】
     给定一个数组，其中有n个元素，分别为红色、白色和蓝色，请将数组中的元素进行排序，使得颜色相同的元素排在一起，并且颜色顺序为红、白、蓝。
     我们使用整数0、1、2分别代表红、白、蓝3种颜色。
     注意：禁止使用标准库提供的排序算法。
     提示：尝试使用三路快速排序的思路以O(n)的时间复杂度解决问题。
     */


    /**
     * 使用选择排序  2ms
     * @param nums
     */
    public void sortColors(int[] nums) {
        for(int i=0;i<nums.length;i++ ){
            int minIndex = i;
            for(int j=minIndex+1;j<nums.length;j++){
                if(nums[minIndex]>nums[j]){
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }

    }

    /**
     * leercode上的 0ms
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int zero =-1;
        int two = nums.length;
        for(int i=0;i<two;){
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 0) {
                nums[i++] = nums[zero + 1];
                nums[++zero] = 0;
            } else {
                if(nums[i] == 2){
                    nums[i] = nums[two - 1];
                    nums[--two] = 2;
                }
            }
        }
    }

}
