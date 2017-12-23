package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Dp10_01Package {

    /**
     * 动态规划10 - 0-1背包问题 - 非leetcode问题，请自行搜索

     有一个背包，容量为C，现在有n种不同的物品，编号为0...n-1，其中每一件物品的重量为w(i)，
     价值为v(i)。问可以向这个背包中盛放哪些物品，使得在不超过背包容量的基础上，物品总价值最大。

     背包问题是动态规划的经典问题，掌握它对于理解动态规划这个算法有重大意义，希望学员们都做一下。
     并且，请查阅相关的优化手段，因为这些优化思想是很通用的。
     */

    /**
     * 动态规划问题适合用于解决最优解问题，比如0/1背包问题.
     背包问题具体例子：假设现有容量10kg的背包，另外有3个物品，分别为a1，a2，a3。物品a1重量为3kg，价值为4；物品a2重量为4kg，价值为5；物品a3重量为5kg，价值为6。将哪些物品放入背包可使得背包中的总价值最大？
     这个问题可以用穷举法实现，但是当数量大了之后，穷举法显然不合适。
     先对这个问题进行数学建模，针对a1，a2……an个物品，它们的重量分别为w1，w2……wn，价值为q1,q1……qn。怎么样放物品使得背包装下的物品的价值最大。
     我们可以假设我们已经求得了c[i-1][m-wi]的最值，那么针对c[i][m]是不是就是考虑当前第i件物品是否要放入背包的问题。由此得到数学公式：
     c[i][m]=max{c[i-1][m-w[i]]+pi , c[i-1][m]}
     所以我们接下来要做的仅仅是实现这个数学公式。代码如下：
     */

    public static void main(String[] args) {
        int m = 10;
        int[] w = new int[]{3,4,5};
        int[] q = new int[]{4,5,6};
        int[][] c = new int[w.length+1][m+1];
        //初始化,动态规划第一步先初始化边界值
        for(int i = 0;i<w.length;i++){
            c[i][0] = 0;
        }
        for(int i = 0;i<m;i++){
            c[0][i] = 0;
        }
        for(int i = 1;i<=w.length;i++){
            for(int j = 1;j<=m;j++){
                if(j>=w[i-1]){
                    if(c[i-1][j-w[i-1]] + q[i-1]>c[i-1][j]){
                        c[i][j] = c[i-1][j-w[i-1]] + q[i-1];
                    }else{
                        c[i][j] = c[i-1][j];
                    }
                }else{
                    c[i][j] = c[i-1][j];
                }

            }
        }
        System.out.println(c[3][10]);
    }
}
