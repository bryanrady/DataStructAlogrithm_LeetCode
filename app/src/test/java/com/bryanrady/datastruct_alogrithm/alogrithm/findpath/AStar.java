package com.bryanrady.datastruct_alogrithm.alogrithm.findpath;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by bryanrady on 2017/12/18.
 *
 * 优化后的A*算法  可直接在这上面扩展
 */

public class AStar {

    //障碍物,如墙、地图边等
    public final static int BAR = 1;
    //走的路径
    public final static int PATH = 2;
    //水平方向和竖直方向的移动代价
    public final static int DIRECT_VALUE = 10;
    //斜方向的移动代价
    public final static int OBLIQUE_VALUE = 14;

    //open队列  存节点周边搜索到的可以走的节点   用PriorityQueue是为了不在自己排序
    Queue<Node> openList = new PriorityQueue<>();
    //closeList
    List<Node> closeList = new ArrayList<>();

    //定义一些辅助性的方法

    /**
     * 算法流程：
     *      1.从起点开始：把起点放入到open列队中
     *
     *      2.节点目标判定: (1)判断open列队是否为Null,不为Null就继续一直走下去
     *                  (2)把open列队的第一个节点N放入close列队中 ，并判断当前节点是不是终点，是：找到终点； 不是：继续往下走
     *
     *      3.节点扩展：计算N的所有能走的节点Vi所对应的F值，并把这些节点存入open列队中
     *                      （1）如果Vi不在open和close中，则加入open,
     *                      （2）如果vi在open中,且新f值小于原f值,则用小f值更新，
     *                     以上2步都需要记录N为vi的父节点
     *
     *      4.估价排序：open表中的节点按f值从小到大排序，我们已经用PriorityQueue进行了排序
     */

    /**
     * 判断当前节点位置是不是终点位置
     * @param end
     * @param coord
     * @return
     */
    public boolean isEndNode(Coord end,Coord coord){
        return coord!=null && end.equals(coord);
    }

    /**
     * 使用曼哈顿距离 计算预估距离 h
     * @param end
     * @param coord
     * @return
     */
    private int calcuH(Coord end, Coord coord) {
        return Math.abs(end.x - coord.x) + Math.abs(end.y - coord.y);
    }

    //开始算法的逻辑

    public void start(MapInfo mapInfo){
        //先判断地图是否存在
        if(mapInfo  == null) return;
        //每次操作都要先清空上一次操作的结果
        openList.clear();
        closeList.clear();

        //1.从起点开始：把起点放入到open列队中
        openList.add(mapInfo.start);
        //2.节点目标判定
        moveNodes(mapInfo);
    }

    /**
     * 节点目标判定  用来移动当前节点
     * @param mapInfo
     */
    private void moveNodes(MapInfo mapInfo) {
        while (!openList.isEmpty()){
            if(isCoordInClose(mapInfo.end.coord)){  //如果这个终点进到了close列队里面，说明找到了终点
                //可以画出结果
                drawPath(mapInfo.map,mapInfo.end);
                break;
            }
            //把open中的第一个节点取出来放到close中
            Node current = openList.poll();
            closeList.add(current);
            //3.节点扩展：开始从current开始的地方找邻近的能走的节点,并添加到open队列中
            addNeighborNodeToOpen(mapInfo,current);
        }
    }

    /**
     * 绘制路径
     * @param map
     * @param end
     */
    private void drawPath(int[][] map, Node end) {
        if(end==null || map==null) return ;
        System.out.println("最短长度为:"+end.g);
        while (end != null){        //从终点往回推
            Coord c = end.coord;
            map[c.y][c.x] = PATH;
            end = end.parent;
        }
    }

    /**
     * 把当前节点的周围8个方向的点添加到open队列中
     * @param mapInfo
     * @param current
     */
    private void addNeighborNodeToOpen(MapInfo mapInfo, Node current) {
        int x = current.coord.x;
        int y = current.coord.y;
        //进行八次操作,定义一个方法来进行一个节点的判断
        //左
        addNeighborNodeToOpen(mapInfo, current, x-1, y, DIRECT_VALUE);
        //上
        addNeighborNodeToOpen(mapInfo, current, x, y-1, DIRECT_VALUE);
        //右
        addNeighborNodeToOpen(mapInfo, current, x+1, y, DIRECT_VALUE);
        //下
        addNeighborNodeToOpen(mapInfo, current, x, y+1, DIRECT_VALUE);
        //左上
        addNeighborNodeToOpen(mapInfo, current, x-1, y-1, OBLIQUE_VALUE);
        //右上
        addNeighborNodeToOpen(mapInfo, current, x+1, y-1, OBLIQUE_VALUE);
        //左下
        addNeighborNodeToOpen(mapInfo, current, x-1, y+1, OBLIQUE_VALUE);
        //右下
        addNeighborNodeToOpen(mapInfo, current, x+1, y+1, OBLIQUE_VALUE);
    }

    /**
     *  添加一个节点到open列队中
     * @param mapInfo
     * @param current
     * @param x
     * @param y
     * @param value
     */
    private void addNeighborNodeToOpen(MapInfo mapInfo, Node current, int x, int y, int value) {
        //首先判断准备走到的节点坐标是否可以添加到open列队中
        if(canAddNodeToOpen(mapInfo,x,y)){
            Node end = mapInfo.end;
            Coord coord = new Coord(x,y);   //这个坐标是准备走到的位置
            int g = current.g + value;      //计算准备走到的点到开始点的距离
            Node child = findNodeInOpen(coord); //查找准备走到的点是否在open队列中，这个child就是上面说的Vi
            if(child == null){  //不在open队列中,表示都是从来没搜过的路
                int h = calcuH(end.coord,coord);    //计算下一个准备走的点到终点的H值
                if(isEndNode(end.coord,coord)){
                    child = end;
                    child.parent = current;
                    child.g = g;
                    child.h = h;
                }else{
                    child = new Node(coord,current,g,h);
                }
                openList.add(child);
            }else if(g < child.g){  //如果在open中(只需要判断两个节点的G值 ,用小的换了
                child.g = g;
                child.parent = current;
            //    openList.add(child); jett加了这句话，我觉得不用
            }
        }
    }

    /**
     * 查找当前点是否在open队列中
     * @param coord
     * @return
     */
    private Node findNodeInOpen(Coord coord) {
        if(coord==null || openList.isEmpty()) return null;
        for(Node node : openList){
            if(node.coord.equals(coord)){
                return node;
            }
        }
        return null;
    }

    /**
     * 判断当前节点坐标是否可以添加到open列队中
     * @param mapInfo
     * @param x
     * @param y
     * @return
     */
    private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y) {
        //先判断当前坐标是不是在地图中
        if(x<0 || x>=mapInfo.width || y<0 || y>=mapInfo.heigh){
            return false;
        }
        //判断是否是不可通过的位置
        if(mapInfo.map[y][x] == BAR){
            return false;
        }
        //判断节点坐标是否在close表中
        if (isCoordInClose(x, y)) {
            return false;
        }
        return true;
    }

    /**
     * 判断节点坐标是否在Close列队中
     * @param coord
     * @return
     */
    private boolean isCoordInClose(Coord coord) {
        return coord!=null && isCoordInClose(coord.x,coord.y);
    }

    /**
     * 判断节点坐标x,y是否在close队列中
     * @param x
     * @param y
     * @return
     */
    private boolean isCoordInClose(int x, int y) {
        if(closeList.isEmpty()) return false;
        for(Node node : closeList){
            if(node.coord.x == x && node.coord.y == y){
                return true;
            }
        }
        return false;
    }

}
