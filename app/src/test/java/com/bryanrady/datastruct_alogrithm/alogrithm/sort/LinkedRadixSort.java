package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import com.bryanrady.datastruct_alogrithm.datastruct.list.SinglyLinkedList;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/7.
 */

public class LinkedRadixSort {

    /**
     * 链式基数排序  在棋牌游戏中很常用
     *               在不加入新的数据情况下对原有数据进行排序效率很高，腾讯的棋牌游戏都是使用这个算法
     *
     *
     *          应用：在斗地主上的删除后排序、麻将初始排序上使用、删除后排序
     */

    @Test
    public void test(){
        SinglyLinkedList<Mahjong> list = new SinglyLinkedList<>();
        list.add(new Mahjong(3,1));
        list.add(new Mahjong(2,3));
        list.add(new Mahjong(3,7));
        list.add(new Mahjong(1,1));
        list.add(new Mahjong(3,8));
        list.add(new Mahjong(2,2));
        list.add(new Mahjong(3,2));
        list.add(new Mahjong(1,3));
        list.add(new Mahjong(3,9));

        for(Object o:list.toArray()){
            Mahjong mahjong = (Mahjong)o;
            System.out.print(mahjong+"\n");
        }
        System.out.println();

        radixSort(list);

        for(Object o:list.toArray()){
            Mahjong mahjong = (Mahjong)o;
            System.out.println(mahjong);
        }

    }

    /**
     * @param list  用单链表保存的一组麻将数据
     */
    public static void radixSort(SinglyLinkedList<Mahjong> list){
        //step1:先初始化要存放点数的集合数组
        SinglyLinkedList[] rankList = new SinglyLinkedList[9];
        for(int i=0;i<rankList.length;i++){
            rankList[i] = new SinglyLinkedList();
        }

        //step2: 根据点数把数据放入对应的组中 把点数为1放入数组0, 2的放入1......
        while (list.size() > 0){
            Mahjong mahjong = list.remove();
            rankList[mahjong.rank - 1].add(mahjong);    //麻将的点数-1就是放入对应的数组下标
        }

        //step3:把9个组的数据连接起来
        for(int i=0;i<rankList.length;i++){
            list.addAll(rankList[i]);
        }

        //step4:初始化要存放花色的集合数组
        SinglyLinkedList[] suitList = new SinglyLinkedList[3];
        for (int i=0;i<suitList.length;i++){
            suitList[i] = new SinglyLinkedList();
        }

        //step5:根据花色把数据放入对应的组中，花色为1放入0.。。。。。
        while (list.size() > 0){
            Mahjong mahjong = list.remove();
            suitList[mahjong.suit - 1].add(mahjong);
        }

        //step6:把3个组的数据连接起来形成一个链表
        for(int i=0;i<suitList.length;i++){
            list.addAll(suitList[i]);
        }
    }

    /**
     * 麻将实体类
     */
    static class Mahjong{
        int suit;   //花色 万 筒 条
        int rank;   //点数大小

        public Mahjong(int suit,int rank){
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Mahjong{" +
                    "suit=" + suit +
                    ", rank=" + rank +
                    '}';
        }
    }

}
