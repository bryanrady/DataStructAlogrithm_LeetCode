package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        q.offer(root);
        int counter = 1;
        while(!q.isEmpty()){
            int localCounter = 0;
            for(int i=0;i<counter;i++){
                TreeNode temp = q.poll();
                if(temp.left!=null){
                    q.offer(temp.left);
                    localCounter++;
                }
                if(temp.right!=null){
                    q.offer(temp.right);
                    localCounter++;
                }
                if(i==counter-1) res.add(temp.val);
            }
            counter=localCounter;
        }
        return res;
    }
}
