package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/7.
 */

public class InsertSort {

    /**
     * 插入排序     使用在3-7个数之间的排序
     *              希尔排序的前身
     */

    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j = i;
            int target = array[i];      //表示想要插入的数据

            while(j>0 && target<array[j-1]){    //如果插入的数据小于数组前面一个时，把前面一个数据赋值到当前位置
                array[j] = array[j-1];
                j--;
            }
            array[j] = target;
        }
    }

    @Test
    public void test(){
        int[] array = new int[]{5,6,1,8,7,4,3,9,2};
        insertSort(array);
        for(int a:array){
            System.out.print(a+" ");
        }
    }
}
