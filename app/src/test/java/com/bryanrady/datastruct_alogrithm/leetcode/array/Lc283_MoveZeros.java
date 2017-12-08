package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/8.
 */

public class Lc283_MoveZeros {

    /**
     *   0的移动
     *   给定一个数组nums，写一个函数，将数组内的0移动到数组末尾，并保持其他非零元素在原数组中的相对位置不变。
     *   比如，给定nums = [0, 1, 0, 3, 12]，调用你的函数之后，nums应该变成[1, 3, 12, 0, 0]。
     *   注意：
     * 1. 请直接在传入的数组对象上修改，而不是另外创建一份拷贝（术语叫做 in-place，也有中译为“原地”）。
     * 2. 尽量减少操作指令代码的行数
     */

    /**
     * leetcode上的解法  2ms
     * @param nums
     */
    public static void moveZeros(int[] nums){
        if(nums == null || nums.length<2){
            return;
        }
        int k = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[k++] = nums[i];
            }
        }
        for(;k<nums.length;k++){
            nums[k] = 0;
        }
    }

    /**
     * leetcode上的解法 3ms
     */
    public static void moveZeros1(int[] nums){
        if(nums == null || nums.length<2){
            return;
        }
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                int temp = nums[k];
                nums[k] = nums[i];
                nums[i] = temp;
                k++;
            }
        }
    }

    /**
     * 一开始自己想的  3ms
     * @param nums
     */
    public static void moveZeros2(int[] nums){
        if(nums == null || nums.length<2){
            return;
        }
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[k] = nums[i];
                k++;
            }
        }
        for(int i=k;i<nums.length;i++){
            nums[k] = nums[i] = 0;
        }
    }
}
