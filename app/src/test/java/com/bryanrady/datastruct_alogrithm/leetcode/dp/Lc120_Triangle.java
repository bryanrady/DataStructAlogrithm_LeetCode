package com.bryanrady.datastruct_alogrithm.leetcode.dp;

import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc120_Triangle {

    /**
     * 动态规划02 - 三角形 - 中等 - 120

     给定一个三角形，找出自顶到底的最小和，每次你只能在下层最邻近的左右两个数之间移动。

     比如：
     [
           [2],
          [3,4],
         [6,5,7],
        [4,1,8,3]
     ]

     这个三角的最小和为2+3+5+1=11。
     */

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0)
            return 0;
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for(int i=1;i<triangle.size();i++){
            List<Integer> path = triangle.get(i);
            dp[i] = dp[i-1]+path.get(path.size()-1);
            for(int j=path.size()-2;j>0;j--){
                dp[j] = Math.min(dp[j],dp[j-1])+path.get(j);
            }
            dp[0] = dp[0]+path.get(0);
        }
        int min = dp[0];
        for(int sum:dp)
            min = Math.min(sum,min);
        return min;
    }
}
