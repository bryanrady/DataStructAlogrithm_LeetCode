package com.bryanrady.datastruct_alogrithm.leetcode.stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by bryanrady on 2017/12/13.
 */

public class Lc71_SimplifyPath {

    /**
     * 栈03 - 简化路径 - 中等 - 71
     给定一个Unix风格的绝对路径，进行简化。
     比如：
     path = "/home/" => "/home"
     path = "/a/./b/../../c/" => "/c"
     注意：
     考虑一些边界条件：
     1. 对于path = "/../" 可以返回"/"；
     2. 路径中可能存在的连续'/'，比如"/home//foo"，这种情况下应该忽略重复的斜杠并返回"/home/foo"。
     */


    /**
     * leetcode 9ms 不适用栈的话更快
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if(path==null || path.length()==0){
            return "";
        }
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();
        for(int i=0;i<s.length;i++){
            if(s[i].equals(".") || s[i].equals("")){
                continue;
            }else if(s[i].equals("..")){
                if(stack.isEmpty()){
                    continue;
                }else{
                    stack.pop();
                }
            }else {
                stack.push(s[i]);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        while (!stack.isEmpty()){
            list.add(0,stack.pop());
        }
        for(int i=0;i<list.size();i++){
            sb.append("/");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

}
