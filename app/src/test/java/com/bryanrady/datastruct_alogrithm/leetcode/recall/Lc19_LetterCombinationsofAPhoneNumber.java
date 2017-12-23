package com.bryanrady.datastruct_alogrithm.leetcode.recall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc19_LetterCombinationsofAPhoneNumber {

    /**
     * 递归回溯01 - 9宫格键盘中的字母组合 - 中等 - 17

     给定一个数字字符串，返回这些数字能在手机的9宫格键盘中组合成的所有字母组合。

     比如：

     给出“23”，则可以组合成：
     ["ab","ae","af","bd","be","bf","cd","ce","cf"]。
     */

    public List<String> letterCombinations(String digits) {
        String[] digits2String={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res=new ArrayList<>();
        int len=digits.length();
        if(len==0)return res;
        char ch;
        String tmp=null;
        for(int i=0;i<len;i++)
        {
            ch=digits.charAt(i);
            if(ch<='9'&&ch>'1')
            {
                tmp=digits2String[ch-'0'];
                res=mergeListAndString(res,tmp);
            }
        }
        return res;
    }

    static List<String> mergeListAndString(List<String> list,String str) {
        if(str==null)return list;
        List<String> resTmp=new ArrayList<String>();
        if(list.size()==0) {
            for(int i=0;i<str.length();i++)
                list.add(String.valueOf(str.charAt(i)));
            return list;
        }else {
            for(int j=0;j<list.size();j++) {
                for(int k=0;k<str.length();k++) {
                    resTmp.add(list.get(j)+str.charAt(k));
                }
            }
            return resTmp;
        }
    }
}
