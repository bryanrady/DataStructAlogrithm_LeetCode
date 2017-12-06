package com.bryanrady.datastruct_alogrithm.alogrithm.search;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class BinarySearch {

    /**
     * 二分查找
     *          前提条件：数据必须是有序的
     *          注意：要设计成左闭又开，一种区间无重复的思想，就像高数上的[0,1)
     *          应用场景：使用顺序存储结构并且数据有序的线性表
     */

    /**
     * 数组在fromIndex和toIndex之间有没有key这个值 并返回下标
     * @param array
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    public static int binarySearch(int[] array,int fromIndex,int toIndex,int key){
        int low = fromIndex;
        int high = toIndex -1;
        while(low < high){
            int middle = (low+high)/2;  //等价于(low+high)>>>1  无符号除以2
            if(key > array[middle]){
                low = middle + 1;
            }else if(key < array[middle]){
                high = middle - 1;
            }else{
                return middle;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        int[] array = new int[]{1,3,4,6,7,9};
        int index = binarySearch(array,0,array.length,7);
        System.out.print(index);
    }
}
