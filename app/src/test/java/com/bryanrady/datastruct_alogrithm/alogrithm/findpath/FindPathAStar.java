package com.bryanrady.datastruct_alogrithm.alogrithm.findpath;

import com.bryanrady.datastruct_alogrithm.datastruct.list.ArrayList;

import org.junit.Test;

/**
 * Created by wqb on 2017/12/12.
 */

public class FindPathAStar {

    /**
     * A*寻路算法       不过目前这个算法还有问题，后面优化
     *         大型网游的寻路算法，LOL，魔兽等都是用这个寻路，不过还要加许多东西
     *         3D游戏的地图都是用的高度图，而这里用数组来模拟
     *
     *         定义起点和终点，然后在起点周围八个方向找到8个节点，用8个节点(中间点)和终点比较，
     *         然后拿到距离终点最近的点，然后把这个点当做当前点，继续找8个方向最近的距离
     *          一直循环直到找到最后的终点为止。
     *
     *          起点到节点（中间点）的距离是已知的  g(n)
     *          节点到终点的距离  估算距离   h(n)
     *          所以起点到终点的距离： 勾股定理  估价函数f(n) = g(n)+h(n)
     *
     *          所以这个问题就转化为节点到终点的最近距离的问题?
     *          通过这样的思想就可以求出最短路径
     */

    /**
     * 用自己写的ArrayList
     * openList保存所有能走的路
     * closeList保存所有不能走的位置
     */
    public static ArrayList<P> openList = new ArrayList<>();
    public static ArrayList<P> closeList = new ArrayList<>();

    //定义起点和终点
    public static P startPoint;
    public static P endPoint;

    public static int[][] map = new int[][]{
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {3,0,0,1,0,0,0,0,3,3,0,0,0,3,3,0,3,3},
            {3,3,3,0,3,3,3,0,3,3,0,3,0,0,0,0,3,3},
            {3,3,3,0,3,3,3,0,0,0,0,3,0,3,3,3,3,3},
            {3,0,0,0,0,0,3,3,3,3,3,0,0,0,0,3,3,3},
            {3,0,3,3,3,0,0,0,0,0,3,0,3,3,2,0,0,3},
            {3,0,0,3,3,3,3,3,3,0,0,0,3,3,3,3,0,3},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    };

    /**
     * 初始化地图
     */
    public static void init(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]==1){
                    openList.add(new P(i,j));
                    startPoint=new P(i,j);
                }else if(map[i][j]==2){
                    closeList.add(new P(i,j));
                    endPoint=new P(i,j);
                }else if(map[i][j]==3){
                    closeList.add(new P(i,j));
                }
            }
        }
    }

    /**
     * 计算点到点的距离
     * @param p1
     * @param p2
     * @return
     */
    public static int pointToPoint(P p1,P p2){
        int a = Math.abs(p1.x - p2.x);
        int b = Math.abs(p1.y - p2.y);
        int c = (int)Math.sqrt(a*a + b*b);
        return c;
    }

    /**
     * 计算起点到节点的距离  已知的真是距离 g(n)
     * @param startPoint
     * @param node
     * @return
     */
    public static int g(P startPoint,P node){
        return pointToPoint(startPoint,node);
    }

    /**
     * 计算节点到终点的距离  估算距离 h(n)
     * @param node
     * @param endPoint
     * @return
     */
    public static int h(P node,P endPoint){
        return pointToPoint(node,endPoint);
    }

    /**
     * 估计函数
     * @param startPoint
     * @param node
     * @param endPoint
     * @return
     */
    public static int f(P startPoint,P node,P endPoint){
        return g(startPoint,node) + h(node,endPoint);
    }

    /**
     * 开始收集所有能走的路
     */
    public static void openFn(P p){
        if(p.x==endPoint.x && p.y == endPoint.y){   //判断当前点是不是终点
            return;
        }
        //标记当前位置
        map[p.x][p.y] = 1;
        //找相邻位置，确认下一个距离终点最近的目标点
        P nextPoint = lookUp(p);

        //递归下一个点
        openFn(nextPoint);
    }

    /**
     * 返回下一个距离终点最近的目标点
     * @param p
     * @return
     */
    private static P lookUp(P p){
        //存放所有能走的路径
        ArrayList<P> resulit = new ArrayList<>();
        //开始搜索自己能走的路
        if(map[p.x+1][p.y]!=3 && map[p.x+1][p.y] !=1){  //下
            resulit.add(new P(p.x+1,p.y));
        }
        if(map[p.x+1][p.y+1]!=3 && map[p.x+1][p.y+1] !=1){  //右下
            resulit.add(new P(p.x+1,p.y+1));
        }
        if(map[p.x][p.y+1]!=3 && map[p.x][p.y+1] !=1){  //右
            resulit.add(new P(p.x,p.y+1));
        }
        if(map[p.x-1][p.y+1]!=3 && map[p.x-1][p.y+1] !=1){  //右上
            resulit.add(new P(p.x+1,p.y));
        }
        if(map[p.x-1][p.y]!=3 && map[p.x-1][p.y] !=1){  //上
            resulit.add(new P(p.x-1,p.y));
        }
        if(map[p.x-1][p.y-1]!=3 && map[p.x-1][p.y-1] !=1){  //左上
            resulit.add(new P(p.x-1,p.y-1));
        }
        if(map[p.x][p.y-1]!=3 && map[p.x][p.y-1] !=1){  //左
            resulit.add(new P(p.x,p.y-1));
        }
        if(map[p.x+1][p.y-1]!=3 && map[p.x+1][p.y-1] !=1){  //左下
            resulit.add(new P(p.x+1,p.y-1));
        }

        //经过上面的判断，result就存放了所有能走的路
        int[] temp = new int[resulit.size()];
        for(int i=0;i<temp.length;i++){
            temp[i] = f(p,resulit.get(i),endPoint);
        }

        //在能用的位置选取最小的   用选择排序找到最小值的位置
        int minIndex = 0;
        for(int i=1;i<temp.length;i++){
            if(temp[minIndex]>temp[i]){
                minIndex = i;
            }
        }

        //保存估算到终点最近的那个点
        P minPoint = resulit.get(minIndex);
        return minPoint;
    }

    @Test
    public void test(){
        init();
        openFn(startPoint);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    static class P{
        int x;
        int y;
        public P(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

}
