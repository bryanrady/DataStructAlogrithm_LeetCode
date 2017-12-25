package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc129_SumRootToLeafNumbers {

    /**
     * 二叉树11 - 根节点到叶子结点的和 - 简单 -  129

     给定一棵二叉树，每个节点都是一个0-9的数字。从根节点到叶子结点的每条路径可以表示成一个数，
     求这些数的和。

     比如：
       1
      / \
     2   3

     1->2，可以表示成12；
     1->3，可以表示成13；
     所以结果为12+13=25。
     */

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    private int sum(TreeNode node, int sum) {
        if(node == null){
            return 0;
        }
        if(node.left == null && node.right == null){
            return sum *10 + node.val;
        }
        return sum(node.left, sum*10 + node.val) + sum(node.right, sum *10 +node.val);
    }
}
