package com.bryanrady.datastruct_alogrithm.alogrithm.findpath;

/**
 * Created by bryanrady on 2017/12/18.
 *
 * 地图信息
 */

public class MapInfo {

    public int map[][]; //用数组来模拟地图，以后就可以直接在这里更改就行了，因为有各种各样的地图
    public int width;   //地图的宽
    public int heigh;   //地图的高
    public Node start;  //起点
    public Node end;    //终点

    public MapInfo(int[][] map, int width, int heigh, Node start, Node end) {
        this.map = map;
        this.width = width;
        this.heigh = heigh;
        this.start = start;
        this.end = end;
    }
}
