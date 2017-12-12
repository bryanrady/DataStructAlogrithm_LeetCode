package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/11.
 */

public class HeapSort {

    /**
     * 堆排序
     *      应用：当数据为无序或链式结构的时候进行二分查找。一般不用来排序，安卓代码用不到，使用在大数据的查找，一般写在后台
     *      堆结构的特点：树必须是完全二叉树，每三个节点中左右孩子都比当前节点小。
     *      过程：1.从最后一个非叶子节点开始，每三个节点做一次大小比较，最小的做根，
     *              如果移动过程中如果子树上的顺序被破坏了，子树上重新调整三个节点的位置
     *            2.取走整个树的根节点，把最后一个叶子作为根节点
     *            3.重复1、2的内容，直到所有的节点被取走了
     */

    @Test
    public void testHeapSort(){
        int[] array=new int[]{6,3,9,2,4,5,1,8,7};
        heapSort(array);
    }

    public static void heapSort(int[] array){
        int n = array.length;   //堆中元素的个数
       //对整个数组建堆,从最后一个非叶子节点(array.length-1)/2开始建堆,一直到根节点
        for(int i=(array.length-1)/2;i>=0;i--){
            createHeap(array,n,i);
        }

        while (n>0){
            System.out.print(array[0]+" ");     //输出根节点
            array[0] = array[n-1];      //把最后一个节点放到根节点上
            n--;
            createHeap(array,n,0);      //从根节点开始重新建堆
        }
    }

    /**
     * 建堆操作
     * @param array
     * @param n     堆中有多少数据
     * @param k     从哪个节点开始建堆
     */
    public static void createHeap(int[] array,int n,int k){
        int kLeft = 2*k + 1;    //k的左孩子的下标
        int kRight = 2*k + 2;   //k的右孩子的下标
        if(kLeft>=n && kRight>=n){    //如果两个下标都越界，表示没有左右孩子
            return;
        }

        int kLeftValue = Integer.MAX_VALUE;     //如果是最大值，代表没有左孩子
        int kRightValue = Integer.MAX_VALUE;
        if(kLeft<n){
            kLeftValue = array[kLeft];
        }
        if(kRight<n){
            kRightValue = array[kRight];
        }

        //对三个节点比较大小
        if(array[k]<kLeftValue && array[k]<kRightValue){    //如果左右孩子都比k小，则不用建堆，直接返回
            return;
        }
        if(kLeftValue < kRightValue){   //如果左孩子比右孩子小，就用左孩子和k交换
            int temp = array[k];
            array[k] = array[kLeft];
            array[kLeft] = temp;
            createHeap(array,n,kLeft);      //为了防止交换数字后破坏树中的堆结构，必须要重新建堆
        }else{
            int temp = array[k];
            array[k] = array[kRight];
            array[kRight] = temp;
            createHeap(array,n,kRight);
        }
    }

}
