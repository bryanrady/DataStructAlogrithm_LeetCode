package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc77_Combinations {

    /**
     * 递归回溯03（组合问题） - 组合 - 中等 - 77

     给定两个整数n和k，求在1...n这n个数字中选出k个数字的所有组合。

     比如n = 4, k = 2，
     则结果为：
     [
     [2,4],
     [3,4],
     [2,3],
     [1,2],
     [1,3],
     [1,4],
     ]

     提示：采用“剪枝”思想尝试进行优化。
     */

    private void solve(List<List<Integer>> res,List<Integer> tmpList,int n,int k,int index){
        if(tmpList.size()==k){
            res.add(new LinkedList<>(tmpList));
            return;
        }
        int size = tmpList.size();
        for(int i = index;i<=n-k+size+1;i++){
            tmpList.add(i);
            solve(res,tmpList,n,k,i+1);
            tmpList.remove(size);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> tmpList = new LinkedList<>();
        solve(res,tmpList,n,k,1);
        return res;
    }
}
