package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class QuickSort {

    /**
     * 快速排序       类似二叉树的前序遍历
     *         应用场景：数据量大并且是顺序存储结构
     *         短处：（1）数据中有大量重复数据的时候性能不好
     *               （2）数据是链式存储结构的时候，性能下降严重
     */

    /**
     * 对数组从begin位置到end位置进行快速排序
     * @param array
     * @param begin
     * @param end
     */
    public static void quickSort(int[] array,int begin,int end){
        if(end <= begin){
            return;
        }

        //step1:定义两个指针下标指向数组两端，并把开始指针处的数据存储起来
        int low = begin;
        int high = end;
        int temp = array[begin];

        //step2:定义两个指针的移动
        boolean direction = true;

        L1:
        while(low < high){
            if(direction) {      //高位指针向左移动
                for (int i = high; i > low; i--) {
                    if (array[i] <= temp) {
                        array[low++] = array[i];  //把这个小的数放到低位指针的位置,并且低位指针向右移动1位
                        high = i;                 //并修改高位指针的位置，让其放置在满足if条件的i上
                        direction = !direction;     //改变指针移动
                        continue L1;
                    }
                }
                high = low;                 //最后两个位置重合即可跳出循环

            }else{
                for(int i=low;i<high;i++){
                    if(array[i] >= temp){
                        array[high--] = array[i];
                        low = i;
                        direction = !direction;
                        continue L1;
                    }
                }
                low = high;

            }
        }

        //step3:把temp放到两个指针重合的位置
        array[low] = temp;
        //array[high] = temp;

        //step4:把放置temp数据的左右两边继续进行快速排序
        quickSort(array,begin,low-1);
        quickSort(array,low+1,end);
    }

    @Test
    public void test(){
        int[] array = new int[]{5,1,8,7,9,4,2,3,6,5};
        quickSort(array,0,array.length-1);
        for(int a:array){
            System.out.print(a+" ");
        }
    }
}
