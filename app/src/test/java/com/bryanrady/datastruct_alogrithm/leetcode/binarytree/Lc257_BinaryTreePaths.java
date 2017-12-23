package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc257_BinaryTreePaths {

    /**
     * 二叉树10 - 二叉树路径 - 简单 - 257

     给定一棵二叉树，返回所有表示从根节点到叶子结点路径的字符串。

     技巧：如何利用递归函数的返回值。

     如：
         1
      /   \
     2     3
      \
       5
     返回结果为：
     ["1->2->5", "1->3"]
     */

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root==null) return res;
        dfs(root,res,"");
        return res;

    }
    public void dfs(TreeNode root, List<String> res, String path){
        if(root==null) return;

        if(root.left==null && root.right==null){
            res.add(path+root.val+"");
        }

        dfs(root.left, res, path+root.val+"->");
        dfs(root.right, res, path+root.val+"->");
    }
}
