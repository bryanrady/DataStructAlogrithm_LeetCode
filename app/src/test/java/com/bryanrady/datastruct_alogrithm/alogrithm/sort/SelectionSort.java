package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class SelectionSort {

    /**
     * 选择排序   快速排序的基础
     */

    public static void selectionSort(int[] array){
        for(int i=0;i<array.length;i++){
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
        selectionSort(array);
        for(int a:array){
            System.out.print(a+" ");
        }
    }

}
