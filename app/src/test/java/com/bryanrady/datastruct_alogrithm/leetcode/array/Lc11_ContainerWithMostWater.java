package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc11_ContainerWithMostWater {

    /**
     * 【题中包含的数组的进阶技术：对撞指针技术】
     *  给出一个非负整数 a1, a2, ..., an,它们分别代表x轴上的一个点(i, ai)，在每个点上画高度为ai的“墙”，
     *  用来代表容器。选择两堵墙，使得它们和x轴围起来的容器装水容量最大。
     *  注意：给出的n>=2。
     */

    /**
     * 12ms
     * @param height
     * @return
     */
    public static int maxArea(int[] height){
        int left = 0;
        int right = height.length-1;
        int maxArea = 0;
        while (left<right && left>=0 && right<height.length){
            maxArea = Math.max(maxArea,(right-left) * Math.min(height[left],height[right]));
            if(height[left]>height[right]){
                right--;
            }else{
                left++;
            }
        }
        return maxArea;
    }

    /**
     * leetcode 7ms
     */
    public static int maxArea2(int[] height){
        int left = 0;
        int right = height.length-1;
        int h = 0;
        int maxArea = 0;
        while (left<right){
            h = Math.min(height[left],height[right]);
            maxArea = Math.max(maxArea,h*(right-left));
            while(left<right && height[left]<=h){
                left++;
            }
            while (left<right && height[right]<=h){
                right--;
            }
        }
        return maxArea;
    }
}
