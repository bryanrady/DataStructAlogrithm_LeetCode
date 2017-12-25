package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc199_BinaryTreeRightSideView {

    /**
     * 二叉树02（队列） - 从右侧观察二叉树的结果 - 中等 - 199

     给定一棵二叉树，相信你站在树的右边观察它，返回你能看到的结点，顺序为自上而下：

     比如：
          1            <---
       /   \
     2     3         <---
      \     \
       5     4       <---
     应该返回[1, 3, 4]
     */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        findRightView(root,result,0);
        return result;
    }

    private void findRightView(TreeNode root,List<Integer> result,int depth){
        if(root==null){
            return;
        }
        if(depth==result.size()){
            result.add(root.val);
        }
        findRightView(root.right,result,depth+1);
        findRightView(root.left,result,depth+1);
    }
}
