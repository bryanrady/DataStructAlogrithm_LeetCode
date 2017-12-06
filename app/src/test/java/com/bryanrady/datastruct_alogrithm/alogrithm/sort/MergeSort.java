package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class MergeSort {

    /**
     * 归并排序  将数组一直分段切割知道只剩下一个元素即可，然后进行排序最后归并
     *          应用场景：数据量大并且是链式存储结构
     *          短处：空间开销大，主要是来自数组的开销
     */

    /**
     * 归并排序  类似二叉树的后序遍历
     * @param array
     * @param begin
     * @param end
     */
    public static void mergeSort(int[] array,int begin,int end){
        if(begin>=end){
            return;
        }else{
            //step1:在两个下标之间取出一个位置作为中间点
            int mid = (begin+end)>>>1;
            //step2:分别对中间两边的数组进行归并排序
            mergeSort(array,begin,mid);
            mergeSort(array,mid+1,end);
            //step3:将左右两边进行归并
            merge(array,begin,mid+1,end);
        }
    }

    /**
     * 归并操作
     * @param array
     * @param begin
     * @param mid
     * @param end
     */                     //                  0          4       7
    private static void merge(int[] array, int begin, int mid, int end) {
        //step1:根据指定长度创建两个数组，
        int leftSize = mid - begin;         //长度为8
        int rightSize = end - mid + 1;   //这里要记得+1，因为上面+1，否则数组长度会比原来少，可以举例子
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        //step2:填充数组,新的数组下标都是从0开始,把原数组的值分配到两个新数组中
        for(int i=begin;i<mid;i++){
            leftArr[i-begin] = array[i];
        }
        for(int j=mid;j<=end;j++){
            rightArr[j-mid] = array[j];
        }
        //step3:合并数组
        int i = 0;  //左边数组下标
        int j = 0;  //右边数组下标
        int k = begin;  //原来数组下标,从begin开始
        while(i<leftSize && j<rightSize){
            if(leftArr[i] < rightArr[j]){
                array[k++] = leftArr[i++];
            }else{
                array[k++] = rightArr[j++];
            }
        }

        while(i<leftSize){
            array[k++] = leftArr[i++];
        }

        while(j<rightSize){
            array[k++] = rightArr[j++];
        }
    }

    @Test
    public void test(){
        int[] array = new int[]{3,2,16,8,9,8,10,15,1,2,3,3,2,31,3,13,4};
        mergeSort(array,0,array.length-1);
        for(int a:array){
            System.out.print(a+" ");
        }
    }

}
