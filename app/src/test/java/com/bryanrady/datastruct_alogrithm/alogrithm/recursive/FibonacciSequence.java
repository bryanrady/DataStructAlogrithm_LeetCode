package com.bryanrady.datastruct_alogrithm.alogrithm.recursive;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class FibonacciSequence {

    /**
     * 递归基础：
     *      一般来说，递归需要边界条件，当边界不满足时，递归前进，当边界条件满足时，递归返回
     *      调用自己一次的情况：调用位置前面的是正循环，调用位置后面的是反循环，即入栈出栈思想
     *      调用自己两次的情况：相当于二叉树的某种遍历过程
     */

    /**
     * 斐波那契数列
     *      1  1  2  3  5  8  13  21  34  55  89  144
     *      f(n) = f(n-1) + f(n-2)
     */

    /**
     * 求第n项的数值
     * @param n
     * @return
     */
    public static int fibonacci(int n){
        if(n==1 || n==2){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    @Test
    public void test(){
        int sum = fibonacci(5);
        System.out.print(sum);
    }


    /**
     * 题目：古典问题：3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
     分析：首先我们要明白题目的意思指的是每个月的兔子总对数；假设将兔子分为小中大三种，兔子从出生后三个月后每个月就会生出一对兔子，
     那么我们假定第一个月的兔子为小兔子，第二个月为中兔子，第三个月之后就为大兔子，那么第一个月分别有1、0、0，第二个月分别为0、1、0，
     第三个月分别为1、0、1，第四个月分别为,1、1、1，第五个月分别为2、1、2，第六个月分别为3、2、3，第七个月分别为5、3、5……
     兔子总数分别为：1、1、2、3、5、8、13……
     于是得出了一个规律，从第三个月起，后面的兔子总数都等于前面两个月的兔子总数之和，即为斐波那契数列。
     */

    /**
     * 求1累加到100的和
     *     100 + sum(99)
     *
     * @param n
     * @return
     */
    public static int sum(int n){
        if(n==1){
            return 1;
        }
        if(n<1){
            return 0;
        }
        return n + sum(n-1);
    }

    @Test
    public void test2(){
        int sum = sum(100);
        System.out.print(sum);
    }

}
