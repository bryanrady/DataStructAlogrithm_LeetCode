package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc226_InvertBinaryTree {

    /**
     * 二叉树04 - 反转二叉树 - 简单 - 226

     这个题目大有来头~当年homebrew的作者去面试Google，就是因为这道基础题做不出来被pass掉了，这在业界曾经引起了广泛的反响。

     Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.

     反转二叉树。
     原树：
             4
           /   \
         2     7
       / \    / \
     1   3   6   9
     反转后：
            4
          /   \
         7     2
       / \    / \
     9   6   3   1
     */

    public TreeNode invertTree(TreeNode root) {
        if(root != null) {
            TreeNode temNode = root.left;
            root.left = root.right;
            root.right = temNode;

            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
