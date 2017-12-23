package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc235_LowestCommonAncestorOfBinarySearchTree {

    /**
     * 二叉树13（二分搜索树） - 二叉搜索树中的最近公共祖先 - 简单 - 235

     给定一棵二叉搜索树和两个节点，寻找这两个节点的最近公共祖先。

     比如：
              6
            /   \
           2     8
         /  \   /  \
        0   4  7    9
          / \
         3   5

     给定2和8，则结果为6。给定2和4则结果为2。
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > q.val && root.val > p.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        else if(root.val < q.val && root.val<p.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        else{
            return root;
        }

    }
}
