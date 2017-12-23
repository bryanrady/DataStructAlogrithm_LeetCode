package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc110_BalancedBinaryTree {

    /**
     *
     二叉树07 - 判断一棵二叉树是否为平衡二叉树 - 简单 - 110

     判断一棵二叉树是否为平衡二叉树。

     平衡二叉树： 每一个节点的左右子树的高度差不超过1。
     */

    static int NOT_BALANCED = -1;
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != NOT_BALANCED;
    }

    int getDepth(TreeNode node){
        if(node == null)
            return 0;
        int left = getDepth(node.left);
        if(left == NOT_BALANCED)
            return NOT_BALANCED;
        int right = getDepth(node.right);
        if(right == NOT_BALANCED)
            return NOT_BALANCED;
        if(Math.abs(left - right) > 1)
            return NOT_BALANCED;
        return Math.max(left, right)+1;
    }
}
