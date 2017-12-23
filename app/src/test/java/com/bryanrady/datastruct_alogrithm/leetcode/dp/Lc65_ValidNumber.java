package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc65_ValidNumber {

    /**
     * 动态规划06 - 寻路 - 中等 - 65

     给定一个二维数组，其中1代表障碍物，0代表通路。
     从左上角出发，每次只能向下或向右，不能通过障碍。
     问一共有多少种走法。

     比如：
     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     一共有2种。

     可以假设m和n均不超过100。
     */

    /**
     * 3ms
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        s = s.trim();
        if (s == null || s.length() == 0) return false;

        boolean numberSeen = false,
                pointSeen = false,
                eSeen = false,
                numberAfterESeen = true;

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c - '0' >= 0 && c - '0' <= 9){
                numberSeen = true;
                numberAfterESeen = true;
            }else if (c == 'e'){
                if (!numberSeen || eSeen) return false;
                eSeen = true;
                numberAfterESeen = false;
            }else if (c == '.'){
                if (pointSeen || eSeen) return false;
                pointSeen = true;
            }else if (c == '+' || c == '-'){
                if (i != 0 && s.charAt(i-1) != 'e') return false;
            }else{
                return false;
            }
        }

        return numberSeen && numberAfterESeen;
    }

    /***
     * 19ms
     * @param s
     * @return
     */
    public boolean isNumber1(String s) {
        boolean flag = true;
        double ss = 0;
        try{
            ss = Double.parseDouble(s);
        }catch(Exception e){
            flag = false;
        }
        return s.charAt(s.length()-1) != 'f' && s.charAt(s.length()-1) != 'd'  && s.charAt(s.length()-1) != 'D'&& flag;

    }
}
