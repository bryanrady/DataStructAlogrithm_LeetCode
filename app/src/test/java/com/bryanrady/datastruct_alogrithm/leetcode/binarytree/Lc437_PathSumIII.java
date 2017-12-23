package com.bryanrady.datastruct_alogrithm.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc437_PathSumIII {

    /**
     * 二叉树12 - 路径和3 - 简单 - 437

     技巧：更加复杂的递归逻辑。

     给出一棵二叉树以及一个数字sum，判断二叉树上存在多少条路径，使其路径上的所有节点的和为sum。

     注意：
     * 其中路径不一定要起始于根节点、终止于叶子结点。
     * 路径虽然可以从任意节点开始，但只能往下走。
     */

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, 0 , sum, map);
    }

    public static int dfs(TreeNode root, int sum, int target, Map<Integer, Integer> map){
        if(root==null)return 0;
        sum+=root.val;
        int res = map.getOrDefault(sum-target,0);
        map.put(sum, map.getOrDefault(sum, 0)+1);
        res += dfs(root.left, sum, target, map )+dfs(root.right, sum, target, map );
        map.put(sum, map.getOrDefault(sum, 0)-1);
        return res;
    }
}
