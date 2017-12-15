package com.bryanrady.datastruct_alogrithm.alogrithm.dp;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/15.
 */

public class KMP {

    /**
     * KMP算法是一种改进的字符串匹配算法，由D.E.Knuth，J.H.Morris和V.R.Pratt同时发现，因此人们称它为克努特——莫里斯——普拉特操作（简称KMP算法）。
     * KMP算法的关键是利用匹配失败后的信息，尽量减少模式串与主串的匹配次数以达到快速匹配的目的
     */

    /**
     * next数组的获取：
     *      一开始的时候 next[0] = 0
     *           next   0 0 1 2               i下标从1开始算  j下标从下面0开始算
     *
     *      模式串      a b a b c a b a
     *                    i
     *                    a b a b c a b a
     *                    j
     */


    /**
     * 求出模式串的next数组  作用：用来帮助我们找到回退的次数的
     * @return
     */
    public static int[] getKmpNext(String pattern ){
        //next数组大小就是字符串长度大小
        int[] next = new int[pattern.length()];
        //将next数组第一个位置直接存0
        next[0] = 0;
        for(int i=1,j=0;i<next.length;i++){
            while (j>0 && pattern.charAt(i) != pattern.charAt(j)){
                j = next[j-1];
            }
            //第1步
            if(pattern.charAt(i) == pattern.charAt(j)){
                j++;
            }
            //第2步
            next[i] = j;
        }
        return next;
    }

    public static int kmp(String dest,String pattern,int[] next){
        for(int i=0,j=0;i<dest.length();i++){
            while (j>0 && dest.charAt(i) != pattern.charAt(j)){  //i=7  j=7 才进的来
                j = next[j-1];  //next[7-1]=2   next[2-1]=0
            }

            if(dest.charAt(i) == pattern.charAt(j)){
                j++;
            }
            if(j == pattern.length()){
                return i-j+1;
            }
        }
        return 0;
    }

    @Test
    public void test(){
        String str = "ababcabcbababcabacaba";
        String dest = "ababcaba";
        int[] array = getKmpNext(dest);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println(kmp(str, dest, array));
    }


}
