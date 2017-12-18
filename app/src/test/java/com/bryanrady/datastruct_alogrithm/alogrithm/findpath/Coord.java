package com.bryanrady.datastruct_alogrithm.alogrithm.findpath;

/**
 * Created by bryanrady on 2017/12/18.
 *
 * 节点的坐标
 */
public class Coord {

    public int x;
    public int y;

    public Coord(int x,int y){
        this.x = x ;
        this.y = y;
    }

    /**
     * 判断节点的两个位置（即坐标）是否相等
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Coord){
            Coord c = (Coord) obj;
            return x==c.x && y==c.y;
        }
        return super.equals(obj);
    }
}
