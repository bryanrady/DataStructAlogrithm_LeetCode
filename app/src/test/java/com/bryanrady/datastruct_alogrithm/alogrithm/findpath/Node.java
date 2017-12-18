package com.bryanrady.datastruct_alogrithm.alogrithm.findpath;

/**
 * Created by bryanrady on 2017/12/18.
 *
 *  节点
 */
public class Node implements Comparable<Node> {

    public Coord coord; //节点坐标
    public Node parent; //节点的父节点
    public int g;   //真实距离
    public int h;   //预估距离

    /**
     * 这个构造方法是为了能把节点坐标存进来
     * @param x
     * @param y
     */
    public Node(int x,int y){
        this.coord = new Coord(x,y);
    }

    public Node(Coord coord,Node parent,int g,int h){
        this.coord = coord;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    /**
     * 用来比较当前节点和另一个节点  到终点的距离 谁更短
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        if(o == null){  //如果是空节点 我们也认为比它小
            return -1;
        }
        if(g+h > o.g+o.h){
            return 1;
        }else if(g+h < o.g+o.h){
            return -1;
        }
        return 0;
    }
}
