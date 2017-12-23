package com.bryanrady.datastruct_alogrithm.leetcode.greedyalogrithm;

import java.util.Arrays;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc455_AssignCookies {

    /**
     * 贪心算法01 - 分蛋糕 - 简单 - 455

     假设你想给小朋友们分蛋糕。每个小朋友最多能够给一块饼干。每个小朋友都有一个“贪心指数”，
     称为g(i)，g(i)表示的是这名小朋友需要的饼干大小的最小值。同时，每个饼干都有一个大小指s(i)。
     如果s(j) >= g(i)， 我们将饼干j分给小朋友i之后，小朋友就会很高兴。给定数组s和g，问
     如何分配饼干，能让更多的小朋友开心。
     */

    public int findContentChildren(int[] g, int[] s) {
        int result = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for(int i=0; result<g.length && i<s.length; i++){
            if(g[result]<=s[i]) result++;
        }
        return result;
    }

    public int findContentChildren1(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i1 = 0;
        int i2 = 0;
        int l1 = g.length;
        int l2 = s.length;
        int res = 0;
        while(i1 < l1 && i2 < l2){
            if(g[i1] <= s[i2]){
                res++;
                i1++;
                i2++;
            }
            else{
                i2++;
            }
        }
        return res;

    }
}
