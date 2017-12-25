package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc222_CountCompleteTreeNodes {

    /**
     * 二叉树06 - 计算完全二叉树的节点个数 - 中等 - 222

     给定一个完全二叉树，求它的节点个数。

     概念：
     完全二叉树： 除了最后一层，其他所有层的节点数达到最大，同时最后一层的所有节点都在最左侧。

     满二叉树： 所有节点数达到最大。
     */

    public int countNodes(TreeNode root){
        if(root == null) return 0;
        int height = height(root);
        TreeNode node = root;
        int sum =  0;
        while( node != null ){
            int rh = height(node.right);
            if(rh == height -1){
                sum += 1 << rh;
                node = node.right;
            }else{
                sum += 1 << rh;
                node = node.left;
            }
            height--;
        }
        return sum;
    }

    /**
     * 完全二叉树中 计算节点的高度就是节点有多少层造孩子
     * @param node
     * @return
     */
    public int height(TreeNode node){
        TreeNode n = node;
        int h = 0;
        while(n != null ){
            h++;
            n = n.left;
        }
        return h;
    }
}
