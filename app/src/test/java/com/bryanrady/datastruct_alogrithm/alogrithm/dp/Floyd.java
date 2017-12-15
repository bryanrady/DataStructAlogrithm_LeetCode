package com.bryanrady.datastruct_alogrithm.alogrithm.dp;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/15.
 */

public class Floyd {

    /**
     * 弗洛伊德算法
     *       1.先生成图的邻接距阵与路径距阵
     *       2.用d[i,j]表示i点到j点的最短距离，但i到j可能会经过K个顶点才能找到最短路径
     *           则d[i][j]=d[i][k]+d[k][j],其中的k可能为多个点
     *       3.遍历全部顶点，如果出现d[i][j]>d[i][k]+d[k][j],我们就用短的路径替换长的路径，如图中v1->v2直接走为4，用v1->v0->v2==3
     *       4.为了找到全部结点对之间的最短路径,可以在路径计算过程中，取数组p[i][j]=p[i][k]用于记录每次的替换过程;
     */


    public static final int INF = Integer.MAX_VALUE;
    //邻接距阵
    public static int[][] d = new int[][]{
            {0, 2, 1, 5},
            {2, 0, 4, INF},
            {1, 4, 0, 3},
            {5, INF, 3, 0}
    };

    //路径数组 把顶点下标先填上去
    public static int[][] p = new int[][]{
            {0,1,2,3},
            {0,1,2,3},
            {0,1,2,3},
            {0,1,2,3}
    };

    /**
     * 打印后的最短距离数组
     *  0 2 1 4
        2 0 3 6
        1 3 0 3
        4 6 3 0
     */
/*          打印的最短路径数组 通过这个退出路径
            0 1 2 2
            0 1 0 0
            0 0 2 3
            2 2 2 3*/

    @Test
    public void test(){
        floyd();
        printShortPath();
    }

    /**
     * 弗洛伊德算法   只是得到了距离，路径还没有找到
     *      整张表更新之后，就会找到每个节点之间的最短距离
     */
    public static void floyd(){
        for(int k=0;k<d.length;k++){
            for(int i=0;i<d.length;i++){
                for(int j=0;j<d[i].length;j++){
                    if(d[i][j] > d[i][k] + d[k][j]){
                        d[i][j] = d[i][k] + d[k][j];
                        //记录下路径
                        p[i][j] = p[i][k];
                    }
                }
            }
        }
        printArray(d);
        printArray(p);
    }

    /**
     * 回推节点到节点之间的路径
     *   0 1 2 2        V1-->V3     （1,3）0  0！=3 （0,3）2  2！=3  （2,3）3 3==3 再回推了  最后解释 1-0-2-3
         0 1 0 0
         0 0 2 3
         2 2 2 3
     *
     */
    public static void printShortPath(){
        for(int i=0;i<d.length;i++){
            for(int j=0;j<d[i].length;j++){
                System.out.print("V"+i+"--->V"+j+" Weight:"+d[i][j]+" path;"+i);
                int k = p[i][j];
                while (k!=j){
                    System.out.print("-->"+k);
                    k = p[k][j];
                }
                System.out.print("-->"+j);
                System.out.println();
            }
        }
    }

    /**
     * 打印二维数组的方法
     * @param array
     */
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }


//
//        //找到以0为中间点的最短路径
//        for(int i=0;i<d.length;i++){
//            for (int j = 0; j < d.length; j++) {
//                if(d[i][j]>d[i][0]+d[0][j]){     //如果当前的值大于找到的路径，这样就会找到每个顶点的最短路径了
//                    d[i][j]=d[i][0]+d[0][j];
//                }
//            }
//        }
    //找到以1为中间点的最短路径
//        for(int i=0;i<d.length;i++){
//            for (int j = 0; j < d.length; j++) {
//                if(d[i][j]>d[i][1]+d[1][j]){
//                    d[i][j]=d[i][1]+d[1][j];
//                }
//            }
//        }
    //找到以2为中间点的最短路径
//        for(int i=0;i<d.length;i++){
//            for (int j = 0; j < d.length; j++) {
//                if(d[i][j]>d[i][2]+d[2][j]){
//                    d[i][j]=d[i][2]+d[2][j];
//                }
//            }
//        }
        //找到以3为中间点的最短路径
//        for(int i=0;i<d.length;i++){
//            for (int j = 0; j < d.length; j++) {
//                if(d[i][j]>d[i][3]+d[3][j]){
//                    d[i][j]=d[i][3]+d[3][j];
//                }
//            }
//        }

}
