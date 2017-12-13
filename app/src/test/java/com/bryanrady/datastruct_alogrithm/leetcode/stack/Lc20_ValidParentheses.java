package com.bryanrady.datastruct_alogrithm.leetcode.stack;

import java.util.Stack;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc20_ValidParentheses {

    /**
     * 栈01 - 括号配对 - 简单 - 20
     给定一个字符串，其中只包含()，[]，{},判定字符串中的括号匹配是否合法。
     比如 “()”，“()[]{}”是合法的，“(}”，“([)]”是非法的。
     */

    /**
     *  leetcode上的 8ms  我不知道括号怎么匹配
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            char b = match(a);
            if(b== ' '){
                if(stack.size()==0 || match(stack.pop())!=a){
                    return false;
                }
            }else{
                stack.push(a);
            }
        }
        return stack.size()==0;
    }

    /**
     * 字符匹配
     * @param a
     * @return
     */
    private char match(char a){
        switch (a){
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                return ' ';
        }
    }
}
