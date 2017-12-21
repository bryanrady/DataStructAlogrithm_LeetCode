package com.bryanrady.datastruct_alogrithm.alogrithm.recall;

import org.junit.Test;

/**
 * Created by wqb on 2017/12/21.
 *
 * 九宫格
 */

public class Kyushu {

    /**
     * 九宫格类型填写规则：
     *      要求横竖斜相加的结果都相等。
     *      （1）把1放到第一行的中间；
     *      （2）往当前格子右上角填入第二个数字，
     *                  如果当前格子右上角的格子是空的，直接填入数据；
     *                  如果当前格子右上角的格子已经有数据了，直接把数据填写在当前格子的正下方。
     */

    @Test
    public void test(){
        start(array);
        printArray(array);
    }

    private final static int n = 5;

    private static int[][] array = new int[n][n];

    public static void start(int[][] array){
        int x = 1; //x代表要填入的值
        //定义出开始要填1的行和列的位置
        int row = 0;    //表示第一行
        int col = n/2;  //表示在中间列

        array[row][col] = x;    //把1填入第一行的中间

        //开始填入后面的所有数字
        while(x < n*n){ //当x=9的时候就退出了循环
            //为了回退到当前位置，我们要先记录原来填入数据的位置
            //这里就体现了回溯的思想
            int tempRow = row;
            int tempCol = col;

            //step1: 往右上找格子
            row--;      //row--的过程可能会跑出九宫格上面，那么就要把row定位到最后一行
            if(row < 0){
                row = n-1;
            }
            col++;  //col++的过程可能会跑到九宫格右边，那么就要把col定位到第一列
            if(col == n){
                col = 0;
            }

            x++;    //下次要填入的数字

            //step2: 开始填入数据
            if(array[row][col] == 0){   //数组初始化的时候，如果没有填入数字，里面的元素就是0，如果右上没填过
                array[row][col] = x;

            }else{      //如果右上已经填过，那就移到当前位置的正下方
                row = tempRow;
                col = tempCol;
                row++;  //往下走一格
                array[row][col] = x;
            }
        }
    }

    public void printArray(int[][] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

}
