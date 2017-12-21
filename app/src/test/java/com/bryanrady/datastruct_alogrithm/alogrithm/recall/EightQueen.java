package com.bryanrady.datastruct_alogrithm.alogrithm.recall;

import org.junit.Test;

/**
 * Created by wqb on 2017/12/21.
 * 八皇后问题
 */

public class EightQueen {

    /**
     * 八皇后 的排列
     *          规则：在棋子的横列竖线方向都只能有一个棋子，一行放一个棋子
     *
     *          最后打印出了64种方式。
     */

    private final static int n = 8;


    //用下标表示行，值表示列
    //即用  n 表示行，array[n] 表示列
    //最后一维数组打印出来的就是arry[n]的值，也就是根据下标摆放在第几列
    private static int[] array = new int[n];


    @Test
    public void test(){
        eightQueen(0);
    }

    /**
     * 从第一行开始放 row=0开始
     * @param row
     */
    public static void eightQueen(int row){
        //递归退出条件
        if(row == n){   //说明每一行都已经摆放好了数据
            printArray();
            return;
        }

        //回溯体现在了for循环这里，比如在第1行的时候，我们就在第一列放入了0，
        // 后面的列没有运行，所以当下面有冲突的时候，会继续吧for循环之后的条件跑完，
        // 直到后面退出循环为止。
        //开始从第一列到最后一列开始放入棋子
        for(int col=0;col<n;col++){
            array[row] = col;   //把第一个先放入第一列
            if(judge(row)){
                eightQueen(row+1);
            }
        }
    }

    /**
     * 先判断当前行要放入的值和原来的值有没有冲突
     * @param n     n代表要放入的棋子的行数
     * @return
     */
    private static boolean judge(int n) {
        for(int i=0;i<n;i++){
            //不同判断在不在同一行了，因为我们是在一行放一个数据

            //(1)判断当前值与前几个值在不在同一列
            //             array[i] = array[n];
            //(2)判断当前值与前几个值在不在一个对角线上
            //       Math.abs(n-i) == Math.abs(array[n]-array[i])
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private static void printArray(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
