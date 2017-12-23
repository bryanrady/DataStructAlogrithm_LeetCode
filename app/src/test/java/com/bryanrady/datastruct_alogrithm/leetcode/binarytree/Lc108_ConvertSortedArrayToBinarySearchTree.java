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

        return helper(0, nums.length - 1, nums);
    }

    private TreeNode helper (int start, int end, int[] nums) {

        if (start > end) {
            return null;
        }


        int mid= start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        //D&C
        root.left = helper(start, mid - 1, nums);
        //System.out.println(leftSub.val);
        root.right = helper(mid + 1, end , nums);
        //System.out.println(rightSub.val);
        return root;
    }

}
