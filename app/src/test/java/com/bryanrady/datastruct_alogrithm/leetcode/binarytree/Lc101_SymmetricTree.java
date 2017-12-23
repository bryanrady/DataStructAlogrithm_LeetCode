package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc101_SymmetricTree {

    /**
     * 二叉树05 - 判断二叉树是否对称 - 简单 - 101

     给定一棵二叉树，检查它是否为对称的。

     比如：
            1
           / \
         2    2
       / \  / \
     3  4  4   3
     是一棵对称的二叉树，而
          1
        / \
      2    2
       \    \
        3     3
     则为非对称。

     注意：
     尝试用递归和迭代两种方式解决。
     */

    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return func(root.left, root.right);
    }

    public boolean func(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return true;
        else if(t1 != null && t2 != null && t1.val == t2.val) {
            return func(t1.left, t2.right) && func(t1.right, t2.left);
        }
        else
            return false;
    }
}
