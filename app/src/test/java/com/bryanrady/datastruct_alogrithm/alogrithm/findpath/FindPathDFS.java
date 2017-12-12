package com.bryanrady.datastruct_alogrithm.alogrithm.findpath;

import org.junit.Test;

/**
 * Created by wqb on 2017/12/12.
 */

public class FindPathDFS {

    /**
     * 寻路算法 深度优先算法寻路,但是这种寻路找不到最短路径
     *      整张地图用map表示，3表示墙，0表示能走的路
     *      用2来表示走过的地方，1表示回退的路
     *
     */

    @Test
    public void test(){
        dfs(startX,startY);
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int startX = 1;
    public static int startY = 2;
    public static int endX = 8;
    public static int endY = 17;

    public final static int map[][] = new int[][]{
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {3,3,3,3,0,0,0,0,3,3,3,3,3,3,0,0,0,3,3,3},
            {3,3,3,0,0,3,3,0,0,3,3,0,3,3,0,3,0,0,0,3},
            {3,3,0,0,3,3,3,3,0,3,0,0,0,0,0,3,3,3,0,3},
            {3,3,3,0,3,3,3,3,0,0,0,3,3,3,3,3,3,0,0,3},
            {3,3,3,0,3,3,3,3,3,3,0,3,3,3,3,3,0,0,3,3},
            {3,0,0,0,0,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3},
            {3,3,0,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,3,3},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    };

    /**
     * 返回值表示是否继续可以走下去
     * @param x     表示二维数组的行
     * @param y     表示二维数组的列
     * @return
     */
    public static boolean dfs(int x,int y){
        if(map[endX][endY] == 2){   //如果到了终点就退出
            return true;
        }
        //用2表示走过的位置，用1表示走不通的时候需要回退的路
        if(map[x][y] == 0){
            map[x][y] = 2;  //把初始点变成2，也就是开始的地方

            //开始摸索自己能走的路
            if(dfs(x,y-1)){ //如果左边能走
                return true;
            }else if(dfs(x+1,y)){   //如果上边能走
                return true;
            }else if(dfs(x,y+1)){   //如果右边能走
                return true;
            }else if(dfs(x+1,y)){  //如果下边能走
                return true;
            }else {     //如果四个位置都不能走,那就回退并标记为1
                map[x][y] = 1;
                return false;
            }
        }else {
            return false;
        }
    }

}
