package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc39_CombinationsSum {

    /**
     * 递归回溯04（组合问题） - 组合之和 - 中等 - 39

     给定一个结合，其中所有元素各不相同，给定一个数组T。寻找该集合中，所有能使得元素和为T的组合。（集合中元素可以多次使用）

     如给定集合nums = [2,3,6,7],T=7，
     返回[[7],[2,2,3]]
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new Integer[target], candidates, candidates.length - 1, 0, target);
        return result;
    }

    private void backtrack(List<List<Integer>> result, Integer[] list, int[] candidates, int start, int len, int remain) {
        if (remain == 0) {
            Integer[] temp = new Integer[len];
            System.arraycopy(list, 0, temp, 0, len);
            result.add(Arrays.asList(temp));
        }

        if (remain <= 0) return;

        for (int i = start; i >= 0; i--) {
            list[len] = candidates[i];
            backtrack(result, list, candidates, i, len + 1, remain - candidates[i]);
        }
    }
}
