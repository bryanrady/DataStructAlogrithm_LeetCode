package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/7.
 */

public class ShellSort {

    /**
     *  希尔排序
     *      当没做一次操作就需要排序一次的，用希尔排序效率最高，就像QQ游戏里的打麻将，摸一张打一张这种场景
     *      因为麻将本身已经排好序.
     *
     * 已知的最好步长序列由Marcin Ciura设计（1，4，10，23，57，132，301，701，1750，…）
     * 这项研究也表明“比较在希尔排序中是最主要的操作，而不是交换。
     * 用这样步长序列的希尔排序比插入排序和堆排序都要快，甚至在小数组中比快速排序还快，
     * 但是在涉及大量数据时希尔排序还是比快速排序慢。
     */


    /**
     * 希尔排序   是插入排序演变而来,当步长为1的时候就是插入排序
     * @param array
     * @param step  表示步长
     */
    public static void shellSort(int[] array,int step){
        //3 9 1 2 5 4 7 8 6     当步长为3的时候就变成了下面的结果  3 2 7 比较 按顺序放置
        //2 5 1 3 8 4 7 9 6
        for(int k=0; k<step; k++){  //对步长的定位,选择每次操作的开始位置
            for(int i=k+step;i<array.length;i=i+step){
                int j=i;
                int target = array[i];
                while(j>step-1 && target<array[j-step]){
                    array[j] = array[j-step];
                    j = j-step;
                }
                array[j] = target;
            }
        }
    }

    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j=i;
            int tartget = array[i];
            while(j>0 && tartget<array[j-1]){
                array[j] = array[j-1];
                j--;
            }
            array[j] = tartget;
        }
    }


    @Test
    public void test(){
        int[] array = new int[]{3,9,1,2,5,4,7,8,6};
        shellSort(array,3);
        shellSort(array,1);
     //   insertSort(array);
        for(int a:array){
            System.out.print(a+" ");
        }
    }

}
