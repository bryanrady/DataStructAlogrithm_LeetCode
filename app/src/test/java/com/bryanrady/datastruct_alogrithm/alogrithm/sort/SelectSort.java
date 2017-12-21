package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class SelectSort {

    /**
     * 选择排序   快速排序的基础
     */

    public static void selectSort(int[] array){
        for(int i=0;i<array.length-1;i++){  //这里不用多算那一次
            int index = i;
            for(int j= i+1;j<array.length;j++){
                if(array[index]>array[j]){
                    index = j;
                }
            }
            if(index != i){
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }

    @Test
    public void test(){
        int[] array = new int[]{6,7,2,4,9,8,5,1,3};
        selectSort(array);
        for(int a:array){
            System.out.print(a+" ");
        }
    }

}
