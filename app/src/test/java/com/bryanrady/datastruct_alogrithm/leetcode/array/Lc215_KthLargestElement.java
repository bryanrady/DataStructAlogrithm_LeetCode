package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc215_KthLargestElement {

    /**
     * 找到数组中第k大的元素 - 中等 - 215
     【题中包含的数组的进阶技术：对撞指针、快速排序】
     在一个无序数组中找到第k大的元素。注意这里的第k大是指在排序顺序中第k大的元素，而不是第k个不同的元素。
     比如：
     给定[3, 2, 1, 5, 6, 4]，k = 2，则应该返回5。
     注意：
     你可以假设k的值是有效的。
     提示：
     使用快速排序的思想可以以O(n)的时间复杂度解决该问题。
     */

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 1) {
            return -1;
        }
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSort(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int temp = nums[start + (end - start) / 2];
        int low = start;
        int high = end;
        while (low <= high) {
            while (low <= high && nums[low] < temp) {
                low++;
            }
            while (low <= high && nums[high] > temp) {
                high--;
            }
            if (low <= high) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                low++;
                high--;
            }
        }
        if (k <= high) {
            return quickSort(nums, start, high, k);
        } else if (k >= low) {
            return quickSort(nums, low, end, k);
        }
        return nums[k];
    }
}
