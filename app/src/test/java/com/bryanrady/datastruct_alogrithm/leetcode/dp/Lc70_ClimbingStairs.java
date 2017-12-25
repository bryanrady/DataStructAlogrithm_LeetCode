package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc70_ClimbingStairs {

    /**
     * 动态规划01 - 爬楼梯 - 简单 - 70

     这个题目实际上是面试中常见的经典智力题（面试毒瘤）。你应该听过这样一个问题，
     有10个台阶，每次你只能上一层或者两层，问一共有多少种上法。

     这里我们把10改成n，请设计算法求解。实际上，它是一个经典的动态规划问题。


     提示：如我们上面说到的，大多数动态规划的题目都可以用递归和记忆化搜索的方法解，
     所以有时间的话请尽量尝试不同的方法，以加深理解。
     */

    public int climbStairs(int n) {
        if(n<=0)
            return 0;
        if(n == 1)
            return 1;

        int oneStep = 1;
        int twoStep = 2;
        for(int i = 2;i < n; i++){
            int temp = twoStep;
            twoStep = twoStep + oneStep;
            oneStep = temp;
        }
        return twoStep;
    }
}
