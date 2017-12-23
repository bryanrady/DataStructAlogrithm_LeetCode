package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc198_HouseRobber {

    /**
     * 动态规划07 - 入室行窃 - 简单 - 198

     你是一个专业的小偷，打算洗劫一条街的所有房子。每个房子里都有不同价值的财物。但是，如果你选择偷窃相邻的两栋房子，则会出发报警系统。编程求出你最多可以安全的偷窃多少价值的财物。

     比如：
     [3,4,1,2]，则返回6[3,(4),1,(2)]，前面是每栋房子的财物值，括号内是我们选择偷窃的房子。
     [4,3,1,2]，则返回6[(4),3,1,(2)]。

     */

    public int rob(int[] nums) {
        int rob = 0, not_rob = 0;
        for(int i=0;i<nums.length;i++){
            int temp = rob;
            rob = not_rob + nums[i];
            not_rob = Math.max(temp,not_rob);
        }
        return Math.max(rob,not_rob);
    }
}
