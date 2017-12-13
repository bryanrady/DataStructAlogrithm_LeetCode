package com.bryanrady.datastruct_alogrithm.leetcode.array;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc76_MinimumWindowSubstring {

    /**
     * 数组11 - 最小窗口子串 - 困难 - 76
     给定一个字符串S和字符串T，在S中寻找最短的子串，包含T中所有的字符。
     比如：
     S=“ADOBECODEBAXC”，T=“ABC”
     结果为“BAXC”。
     */

    /**
     *  leetcode 2ms
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        int[] hash = new int[256];
        char[] tChar = t.toCharArray();
        char[] sChar = s.toCharArray();
        int count = 0;
        for (int i = 0; i < tChar.length; ++i) {
            ++count;
            ++hash[tChar[i]];
        }

        int start = 0, end = 0;
        int minLen = Integer.MAX_VALUE, start_index = -1;
        while (end < sChar.length) {
            if (hash[sChar[end++]]-- >= 1) {
                --count;
            }
            while (count == 0) {
                if (end - start < minLen) {
                    start_index = start;
                    minLen = end - start;
                }
                if (hash[sChar[start++]]++ >= 0) {
                    ++count;
                }
            }
        }

        if (start_index == -1) {
            return "";
        }

        return s.substring(start_index, start_index + minLen);
    }

    /**
     * leetcode 3ms
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        int[] hash = new int[256];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < T.length; i++) {
            hash[T[i]]++;
        }
        int count = T.length;
        int begin = 0;
        int end = 0;
        int head = 0;
        int min = Integer.MAX_VALUE;
        while (end < S.length) {
            if (hash[S[end++]]-- > 0) {
                count--;
            }
            while (count == 0) {
                if (min > end - begin) {
                    min = end - begin;
                    head = begin;
                }
                if (hash[S[begin++]]++ == 0) {
                    count++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(head, head + min);
    }
}
