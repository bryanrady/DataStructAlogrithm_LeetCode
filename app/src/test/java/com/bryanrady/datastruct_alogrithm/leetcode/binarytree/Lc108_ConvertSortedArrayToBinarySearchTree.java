package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc108_ConvertSortedArrayToBinarySearchTree {

    /**
     * 二叉树15（二分搜索树） - 将有序数组转换为一颗平衡的二叉搜索树 - 简单 - 108

     给定一个有序数组，生成一棵平衡的二叉搜索树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sort(0, nums.length - 1, nums);
    }

    private TreeNode sort (int start, int end, int[] nums) {
        if (start > end) {
            return null;
        }
        int mid= start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sort(start, mid - 1, nums);
        root.right = sort(mid + 1, end , nums);
        return root;
    }

}
