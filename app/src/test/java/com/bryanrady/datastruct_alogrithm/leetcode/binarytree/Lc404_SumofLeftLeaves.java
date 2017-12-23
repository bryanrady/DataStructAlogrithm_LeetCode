package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc404_SumofLeftLeaves {

    /**
     * 二叉树09 - 左叶子的和 - 简单 - 404

     找到树中所有左叶子的和。

     比如：
          3
         / \
        9  20
      /  \
     15   7
     这棵树中有两个左叶子，9和15，因此返回结果应该为24
     */

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int sum = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}
