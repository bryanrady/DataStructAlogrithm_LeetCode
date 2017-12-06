package com.bryanrady.datastruct_alogrithm.alogrithm.search;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class SeqSearch {

    /**
     * 顺序查找
     *          应用场景：  （1）如果线性表中的元素是无序的，则必须使用顺序查找
     *                      （2）如果线性表中的元素是有序的，但采用的是链式存储结构，则也必须使用顺序查找
     */

    /**
     * 返回要查找的数在数组中的下标
     * @param array
     * @param des
     * @return
     */
    public static int seqSearch(int[] array,int des){
        for(int i=0;i<array.length;i++){
            if(array[i]==des){
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        int[] array = new int[]{2,8,5,4,9,6};
        int index = seqSearch(array,5);
        System.out.print(index);
    }


}
