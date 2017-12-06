package com.bryanrady.datastruct_alogrithm.alogrithm.sort;

import org.junit.Test;

/**
 * Created by bryanrady on 2017/12/6.
 */

public class BubbleSort {

    /**
     * 冒泡排序
     *      应用场景：数据量小在8个以内，斗牛游戏的纸牌游戏排序（多关键字排序）
     */

    public static void bubbleSort(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = true;    //如果是true,就证明比较的两个数据是有序的，不用进行交换，可以减少排序次数
            for(int j=0;j<i;j++){
                if(array[j]> array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 纸牌排序
     * @param cards
     */
    public static void bubbleSort(Cards[] cards){
        for(int i=cards.length-1;i>0;i--){
            boolean flag = true;
            for(int j=0;j<i;j++){
                if(cards[j].compareTo(cards[j+1])>0){
                    Cards temp = cards[j];
                    cards[j] = cards[j+1];
                    cards[j+1] = temp;
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }


    static class Cards implements Comparable{

        int points;
        int colors;

        public Cards(int points,int colors){
            this.points = points;
            this.colors = colors;
        }

        @Override
        public int compareTo(Object o) {
            Cards c = (Cards)o;
            if(this.points > c.points){
                return 1;
            }else if(this.points < c.points){
                return -1;
            }
            if(this.colors > c.colors){
                return 1;
            }else if(this.colors <c.colors){
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Cards{" +
                    "points=" + points +
                    ", colors=" + colors +
                    '}';
        }
    }


    @Test
    public void test(){
        int[] array = new int[]{5,9,1,7,3,4,8,6,2,3};
        bubbleSort(array);
        for(int a:array){
            System.out.print(a+" ");
        }
    }

    @Test
    public void test2(){
        Cards[] cardses = new Cards[]{
                new Cards(4,3),
                new Cards(7,2),
                new Cards(2,4),
                new Cards(7,1),
                new Cards(9,2)
        };
        bubbleSort(cardses);
        for(Cards c:cardses){
            System.out.println(c);
        }
    }
}
