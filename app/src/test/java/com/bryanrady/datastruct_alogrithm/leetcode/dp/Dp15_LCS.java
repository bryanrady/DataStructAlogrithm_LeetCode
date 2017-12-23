package com.bryanrady.datastruct_alogrithm.leetcode.dp;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by wqb on 2017/12/23.
 */

public class Dp15_LCS {

    /**
     * 动态规划15(LCS问题) - 最长公共子序列 - 非leetcode

     （面试毒瘤，请务必解决。）

     给出两个字符串S1和S2，求着两个字符串的最长公共子序列的长度。

     比如：
     S1=AA[ACC]G[T]G[AG]T[TA]TT[C]G[TT]C[T]A[G]AA
     S2=C[A]CC[CCTA]AG[GTAC]C[TTTG]GTTC
     方括号内为两个字符串的公共子序列。
     */

    @Test
    public void test(){
        getLcs("AA[ACC]G[T]G[AG]T[TA]TT[C]G[TT]C[T]A[G]AA","C[A]CC[CCTA]AG[GTAC]C[TTTG]GTTC");
    }

    public static void getLcs(String x,String y){
        if(x==null || y==null){
            return;
        }
        char[] s1 = x.toCharArray();
        char[] s2 = y.toCharArray();

        //创建一个二维数组，要多出一个长度，把第一行和第一列存0
        int[][] array = new int[s1.length+1][s2.length+1];

        //在第一行填上0
        for(int i=0;i<array[0].length;i++){
            array[0][i] = 0;
        }
        //在第一列填上0
        for(int i=0;i<array.length;i++){
            array[i][0] = 0;
        }

        //使用动态规划的方式填数据，如果行和列的字符相同就是左上角+1，不同则取左边和上边的最大值
        for(int i=1;i<array.length;i++){
            for(int j=1;j<array[i].length;j++){
                if(s1[i-1] == s2[j-1]){ //二维数组的行下标1和列下标1就是两个字符数组的下标0
                    array[i][j] = array[i-1][j-1]+1; //相同等于左上加1
                }else{
                    array[i][j] = max(array[i-1][j],array[i][j-1]);
                }
            }
        }       //填表完成

/*        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }*/

        //从后往前找到结果，从最后一个数开始找，如果两个字符相同就找左上，不同就找左上最大值其中一个，
        //为了方便我们一般取上
        Stack stack = new Stack();
        //先定位到最后
        int i = x.length()-1;
        int j = y.length()-1;
        while (i>=0 && j>=0){
            if(s1[i] == s2[j]){
                stack.push(s1[i]);  //然后下一个取左上
                i--;
                j--;
            }else{  //注意二维数组和字符数组中的位置有一位的差,所以要加1
                if(array[i+1][j-1+1]>array[i-1+1][j+1]){  //如果左边大,下一个就就取左边
                    j--;
                }else{
                    i--;        //下一个取右边
                }
            }
        }
    //    System.out.println("------------");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }

    private static int max(int a,int b){
        return (a>b)?a:b;
    }
}
