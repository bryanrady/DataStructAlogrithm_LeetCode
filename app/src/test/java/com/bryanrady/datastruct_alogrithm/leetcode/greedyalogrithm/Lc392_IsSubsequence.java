package com.bryanrady.datastruct_alogrithm.leetcode.greedyalogrithm;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc392_IsSubsequence {

    /**
     * 贪心算法02 - 是否是子序列 - 中等 - 392

     给定一个字符串s和t，检查s是否是t的子串。

     你可以假设所有字符串都是小写字母组成，并且字符串t大小很长（大约500,000)个字符，
     而s较短（小于100）。

     注意通过下面的例子正确理解什么是子序列：
     如s="abc",t="ahbgdc"，则为true；
     如s="axc",t="ahbgdc"，则为false。

     */

    public boolean isSubsequence(String s,String t) {
        int start = 0;
        for(char c : s.toCharArray()) {
            start = t.indexOf(c, start) + 1;
            if(start == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence1(String s, String t) {
        if (s.length() == 0) {
            return true;
        }

        if (t.length() < s.length()) {
            return false;
        }

        char[] sch = s.toCharArray();
        char[] tch = t.toCharArray();

        int i = 0;
        for (int j = 0; j < tch.length; ++j) {
            if (sch[i] == tch[j]) {
                if (++i == sch.length) {
                    return true;
                }
            }
        }
        return false;
    }
}
