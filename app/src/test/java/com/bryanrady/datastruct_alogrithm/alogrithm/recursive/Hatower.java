package com.bryanrady.datastruct_alogrithm.alogrithm.recursive;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class Hatower {

    /**
     * 汉诺塔
     *      3根柱子  把第一个柱子上的n个盘子移到最后根柱子上
     *          底下最大的盘子只能单独移动
     */

    /**
     * 这个打印结果就是二叉树的中序遍历过程
     *
     * @param n         盘子个数
     * @param start     开始柱子
     * @param mid       中间过渡柱子
     * @param end       结束柱子
     */
    public static void hatower(int n,int start,int mid,int end){
        if(n<=0){
            return;
        }
        if(n==1){   //只有一个盘子
            System.out.println(start+"----->"+end);
        }else{
            hatower(n-1,start,end,mid);
            System.out.println(start+"----->"+end);
            hatower(n-1,mid,start,end);
        }
    }

    @Test
    public void test(){
        hatower(5,1,2,3);
    }

}
