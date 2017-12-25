package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc112_PathSum {

    /**
     * 二叉树08 - 路径和 - 简单 - 112

     给出一棵二叉树以及一个数字sum，判断在这棵二叉树上是否存在一条从根节点到叶子的路径，
     其路径上的所有节点和为sum。

     技巧：如何在递归过程中保存数据。

     比如：
              5
             / \
           4   8
          /   / \
         11  13  4
       /  \      \
      7    2      1
     如果sum = 22，则可以找到这条路径满足条件： 5->4->11->2。
     */

    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null&&sum==root.val){
            return true;
        }
        return hasPathSum(root.left, sum-root.val)||hasPathSum(root.right, sum-root.val);//递归调用
    }
}
