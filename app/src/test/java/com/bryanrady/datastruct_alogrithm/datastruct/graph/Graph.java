package com.bryanrady.datastruct_alogrithm.datastruct.graph;

/**
 * Created by bryanrady on 2017/12/8.
 */

import java.util.LinkedList;

/**
 * 图论
 */
public class Graph {

    private int vertexSize;     //图的顶点数量

    private int[] vertex;       //存放图顶点的数组
    private int[][] edgeMatrix;     //存放图的边集合,邻接矩阵

    private boolean[] isVisited;    //判断中的顶点是否被访问过

    public final static int MAX_WEIGHT = -1;

    public Graph(int vertexSize){
        this.vertexSize = vertexSize;
        vertex = new int[vertexSize];
        edgeMatrix = new int[vertexSize][vertexSize];
        isVisited = new boolean[vertexSize];
    }

    /**
     * 获取图中所有的顶点
     * @return
     */
    public int[] getVertex(){
        return vertex;
    }

    /**
     * 返回图中顶点的数量
     * @return
     */
    public int getVertexSize(){
        return vertexSize;
    }

    /**
     * 获取顶点v1到顶点v2的权重
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        int weight = edgeMatrix[v1][v2];
        return weight>=0 ? weight : MAX_WEIGHT;
    }

    /**
     * 获取顶点v的出度 在矩阵中横出 竖入
     * @param v
     * @return
     */
    public int getOutDegree(int v){
        int count = 0;
        for(int i=0;i<vertexSize;i++){
            if(edgeMatrix[v][i]>0 && edgeMatrix[v][i] != MAX_WEIGHT){
                count++;
            }
        }
        return count;
    }

    /**
     * 获取顶底v的入度
     * @param v
     * @return
     */
    public int getInDegree(int v){
        int count = 0;
        for(int i=0;i<vertexSize;i++){
            if(edgeMatrix[i][v]>0 && edgeMatrix[i][v] != MAX_WEIGHT){
                count++;
            }
        }
        return count;
    }

    /**
     * 获取节点v的第一个邻接点 返回邻接点位置的下标
     * @param v
     * @return
     */
    public int getFirstNeightBor(int v){
        for(int i=0;i<vertexSize;i++){
            if(edgeMatrix[v][i]>0 && edgeMatrix[v][i]!=MAX_WEIGHT){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取顶点v的邻接点index的下一个邻接点  返回下标
     * @param v
     * @param index 是一个邻接点
     * @return
     */
    public int getNextNeightBor(int v,int index){
        for(int i=index+1;i<vertexSize;i++){
            if(edgeMatrix[v][i]>0 && edgeMatrix[v][i]!=MAX_WEIGHT){
                return i;
            }
        }
        return -1;
    }

    /**
     * 对图论进行深度优先遍历
     */
    public void depthFirstTranversal(){
        for(int i=0;i<vertexSize;i++){
            if(!isVisited[i]){  //如果节点没有被访问过，那就进行访问并输出
                System.out.print("depth visited vertex=="+i);
                isVisited[i] = true;
                depthFirstTranversal(i);
            }
        }
    }

    /**
     * 对节点v进行深度优先遍历
     * @param v
     */
    private void depthFirstTranversal(int v){
        int curr = getFirstNeightBor(v);
        while (curr != -1){ //-1代表没有邻接点
            if(!isVisited[curr]){
                System.out.print("depth visited vertex=="+curr);
                isVisited[curr] = true;
                depthFirstTranversal(curr);	//然后对当前节点进行深度优先遍历
            }
            curr = getNextNeightBor(v,curr);
        }
    }

    /**
     * 对图论进行广度优先遍历
     */
    public void breadthFirstTranversal(){
        for(int i=0;i<vertexSize;i++){
            if(!isVisited[i]){
                System.out.print("breadth visited vertex=="+i);
                isVisited[i] = true;
                breadthFirstTranversal(i);
            }
        }
    }

    /**
     * 对节点v进行广度优先遍历
     * @param v
     */
    private void breadthFirstTranversal(int v){
        LinkedList<Integer> queue = new LinkedList<>();
        int fn = getFirstNeightBor(v);
        if(fn == -1){
            return;
        }
        if(!isVisited[fn]){
            System.out.print("breadth visited vertex=="+fn);
            isVisited[fn] = true;
            queue.offer(fn);
        }
        //访问其他邻接点
        int next = getNextNeightBor(v,fn);
        while (next != -1){
            if(!isVisited[next]){
                System.out.print("breadth visited vertex=="+next);
                isVisited[next] = true;
                queue.offer(next);
            }
            next = getNextNeightBor(v,next);
        }
        while (!queue.isEmpty()){
            int vertex = queue.poll();
            breadthFirstTranversal(vertex);
        }
    }

}
