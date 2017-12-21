package com.bryanrady.datastruct_alogrithm.alogrithm.recall;

import org.junit.Test;

/**
 * Created by wqb on 2017/12/21.
 *  数独问题
 */

public class Sudoku {

    /**
     * 数独的玩法：
     *        把9*9=81的二维数组分成9个宫，把1—9的数据填入格子中，填入数据的要求：
     *
     *          （1）每一行不能有重复的数据；
     *          （2）每一列不能有重复的数据
     *          （3）每一个宫不能有重复的数据；
     */

 //   public static int[][] array = new int[9][9];  //有92中数独解

    //给定一个数独游戏，要求吧剩下的空格0填入数据
    public static int[][] array={
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    @Test
    public void test(){
        sudoku();
    }

    /**
     * 开始数独游戏，从第一格开始填
     */
    public static void sudoku(){
        sudoku(0,0);
    }

    /**
     * 找位置填数据
     * @param row
     * @param col
     */
    private static void sudoku(int row,int col){

        //递归退出条件
        if(row == 8 && col == 9){   //说明81个格子全部填完
            printResult();
            return;
        }

        //如果把某一行全部填满了，就要调到下一行的第一列继续开始填
        if(col == 9){
            row++;
            col = 0;
        }

        //填数据
        if(array[row][col] == 0){   //如果要填数据的位置是空格子
            for(int k=1;k<=9;k++){  //k是要填入的数据
                if(judge(row,col,k)){
                    array[row][col] = k;    //把要填入的数据填入当前格子
                    sudoku(row,col+1);  //填完数据之后找当前行的下一列

                    //当我们后面有了冲突后，无法定位上一次填入数据的位置，所以必须要还原
                    //这一步容易忘记，必须让前一次填入数据的格子还原，这也体现了回溯的过程
                    array[row][col] = 0;
                }
            }
        }else{      //如果不是空格子，直接找当前行的下一列
            sudoku(row,col+1);
        }
    }

    /**
     * 判断要在当前位置要填入的数据满不满足数独的要求
     * @param row
     * @param col
     * @param number
     * @return  重复就返回false
     */
    private static boolean judge(int row,int col,int number){
        //step1:判断要填入的数据所在的行和列里面有没有重复值
        //判断要填入的数据和当前行的数据重不重复  array[row][i] == number
        //判断要填入的数据和当前列的数据重不重复  array[i][col] == number
        for(int i=0;i<9;i++){
            if(array[row][i] == number || array[i][col] == number){
                return false;
            }
        }

        //step2:判断要填入的数据所在的宫里面有没有重复值

        //把行和列都除以3就得到了number所在宫的左上角的格子
        int tempRow = row/3;
        int tempCol = col/3;
        //把数据在当前宫内判断
        for(int i=0;i<3;i++){   //当前宫内行有3个格子
            for(int j=0;j<3;j++){ //当前宫内列也有3个格子
                //tempRow*3 + 0 tempCol*3 + 0 就得到了宫内第一行行的第一个格子
                //tempRow*3 + 0 tempCol*3 + 1 就得到了宫内第一行行的第二个格子
                //tempRow*3 + 0 tempCol*3 + 2 就得到了宫内第一行行的第三个格子
                //tempRow*3 + 1 tempCol*3 + 2 就得到了宫内第二行行的第一个格子
                //.......
                // 一直循环下来就遍历了宫内的所有格子

                if(array[tempRow*3 + i][tempCol*3 + j] == number){
                    return false;
                }
            }
        }

        return true;
    }

    private static void printResult(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
