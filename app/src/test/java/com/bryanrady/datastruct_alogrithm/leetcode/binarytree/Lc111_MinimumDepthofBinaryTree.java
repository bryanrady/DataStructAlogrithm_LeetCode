package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc111_MinimumDepthofBinaryTree {

    /**
     * 二叉树03 - 二叉树最低深度 - 简单 - 111

     求一棵二叉树的最低深度，也就是从根节点到叶子结点的最短路径的长度。
     */


    /**
     * 0.5ms
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        // I peek the answer
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0) return 1 + right;
        if (right == 0) return 1 + left;
        return 1 + Math.min(left, right);
        // return (left == 0 || right == 0) ? 1 + left + right : 1 + Math.min(left, right);
    }

    public int minDepth1(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null){
            return minDepth1(root.right) + 1;
        }else if (root.right == null){
            return minDepth1(root.left) + 1;
        }
        return Math.min(minDepth1(root.left), minDepth1(root.right)) + 1;
    }
}
