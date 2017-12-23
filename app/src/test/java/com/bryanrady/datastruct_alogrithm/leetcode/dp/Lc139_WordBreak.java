package com.bryanrady.datastruct_alogrithm.leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc139_WordBreak {

    /**
     * 动态规划12（背包问题变种） - 单词分解 - 中等 - 139

     给定一个非空字符串s和一个字符串数组wordDict，问能否使用wordDict中的不同字符串首尾相接，组成s。假定wordDict中没有重复的字符串。

     如s="leetcode"，dict=["leet","code"]，则返回true。
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] arr = new boolean[len + 1];
        arr[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = 0; j < wordDict.size(); j++){
                int wordLen = wordDict.get(j).length();
                if(wordLen <= i){
                    if(arr[i - wordLen] && s.substring(i-wordLen, i).equals(wordDict.get(j))){
                        arr[i] = true;
                        break;
                    }
                }
            }
        }
        return arr[len];
    }

    /**
     * 3ms
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        if (s == null) {
            return false;
        }
        int maxLength = 0;
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            set.add(word);
        }
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= maxLength && j <= i; j ++) {
                if (f[i - j]) {
                    String sub = s.substring(i - j, i);
                    if (set.contains(sub)) {
                        f[i] = true;
                        break;
                    }
                }
            }
        }
        return f[n];
    }
}
