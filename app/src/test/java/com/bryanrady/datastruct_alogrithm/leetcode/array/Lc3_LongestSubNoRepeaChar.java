package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc3_LongestSubNoRepeaChar {

    /**
     * 数组10 - 没有重复字符的最长子串 - 中等 - 3
     【题中包含的数组的进阶技术：滑动窗口技术】
     在一个字符串中寻找没有重复字母的最长子串
     比如：
     “abcabcbb”,结果为“abc”
     “bbbbbb”，结果为“b”
     “pwwkew”，结果为“wke”
     */

    /**
     * leetcode  37ms 最优
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if(2 > chars.length){
            return chars.length;
        }
        int max = 0;
        int split_at = 0;
        int cur_len = 1;
        for(int i=1;i<chars.length;i++){
            int j = split_at;
            for(;j<i;j++){
                if(chars[i] == chars[j]){
                    break;
                }
            }
            if(j < i){
                split_at = j+1;
                cur_len = i-j;
            }else{
                cur_len++;
            }
            if(cur_len > max) max = cur_len;
        }
        return max;
    }
}
