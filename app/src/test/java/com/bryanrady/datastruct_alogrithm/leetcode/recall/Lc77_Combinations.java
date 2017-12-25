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

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new LinkedList<>();
        List<Integer> tempList = new LinkedList<>();
        solve(list,tempList,n,k,1);
        return list;
    }

    private void solve(List<List<Integer>> list,List<Integer> tempList,int n,int k,int index){
        if(tempList.size() == k){
            list.add(new LinkedList<>(tempList));
            return;
        }
        int size = tempList.size();
        for(int i = index;i<=n-k+size+1;i++){
            tempList.add(i);
            solve(list,tempList,n,k,i+1);
            tempList.remove(size);
        }
    }

}
