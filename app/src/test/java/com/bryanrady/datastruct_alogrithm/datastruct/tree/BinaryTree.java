package com.bryanrady.datastruct_alogrithm.datastruct.tree;

import org.junit.Test;

/**
 * Created by wqb on 2017/12/7.
 */

public class BinaryTree<E> {

    /**
     * 二叉树的数据结构
     * @param <E>
     */
    static class TreeNode<E>{
        E data;
        TreeNode<E> parent;
        TreeNode<E> leftChild;
        TreeNode<E> rightChild;

        TreeNode(E data){
            this.data = data;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public TreeNode<E> root;   //根节点

    public BinaryTree(TreeNode<E> root){
        this.root = root;
    }

    /**
     * 使用递归实现二叉树的前序遍历
     * @param root
     */
    public void preOrderTranverse(TreeNode<E> root){
        if(root == null){
            return;
        }
        System.out.print("preOrder:"+root.data);
        preOrderTranverse(root.leftChild);
        preOrderTranverse(root.rightChild);
    }

    /**
     * 中序遍历
     * @param root
     */
    public void minOrderTranverse(TreeNode<E> root){
        if(root == null){
            minOrderTranverse(root.leftChild);
            System.out.print("midOrder:"+root.data);
            minOrderTranverse(root.rightChild);
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrderTranverse(TreeNode<E> root){
        if(root == null){
            postOrderTranverse(root.leftChild);
            postOrderTranverse(root.rightChild);
            System.out.print("postOrder:"+root.data);
        }
    }


}
