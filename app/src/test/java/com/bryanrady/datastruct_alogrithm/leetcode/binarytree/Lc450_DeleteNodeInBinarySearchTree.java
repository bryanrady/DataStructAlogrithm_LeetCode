package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc450_DeleteNodeInBinarySearchTree {

    /**
     * 二叉树14（二分搜索树） - 在二分搜索树中删除一个节点 - 中等 - 450

     给定一个二分搜索树，删除其中一个节点。

     一般来说，删除操作可以分为两个不走：
     1. 查找到要删除的那个节点
     2. 如果找到，则删除它。

     注意：
     时间复杂度至少得小于等于O(树的高度)

     比如：

     root = [5,3,6,2,4,null,7]
     key = 3

         5
        / \
       3   6
      / \   \
     2   4   7

     给定要删除的节点为3。
     其中一个可行的答案为[5,4,6,2,null,null,7]，如下：

         5
        / \
       4   6
      /     \
     2       7

     另一种有效的答案为 [5,2,6,null,4,null,7].

        5
      /  \
     2    6
      \    \
      4     7
     */

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {

            if (root.right == null) {
                root = root.left;

            } else if (root.left == null){
                root = root.right;

            } else {
                int min = findMin(root.right);
                root.val = min;
                root.right = deleteNode(root.right, min);
            }
        }
        return root;
    }

    private int findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}
