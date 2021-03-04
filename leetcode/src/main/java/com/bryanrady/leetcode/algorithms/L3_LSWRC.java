package com.bryanrady.leetcode.algorithms;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L3_LSWRC {

//    Longest Substring Without Repeating Characters  无重复字符的最长子字符串
//
//    Example 1:
//
//    Input: s = "abcabcbb"
//    Output: 3
//    Explanation: The answer is "abc", with the length of 3.
//    Example 2:
//
//    Input: s = "bbbbb"
//    Output: 1
//    Explanation: The answer is "b", with the length of 1.
//    Example 3:
//
//    Input: s = "pwwkew"
//    Output: 3
//    Explanation: The answer is "wke", with the length of 3.
//    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
//            Example 4:
//
//    Input: s = ""
//    Output: 0

    /**
     * 蛮力法  耗时太长
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            for (int j = i+1; j <= s.length(); j++){
                if (allUnique(s, i, j)){
                    ans = Math.max(ans, j-i);
                }
            }
        }
        return ans;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++){
            Character ch = s.charAt(i);
            if (set.contains(ch)){
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /**
     * 7ms
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < s.length() && j < s.length()){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j-i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 9ms
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++){
            if (map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }

}
