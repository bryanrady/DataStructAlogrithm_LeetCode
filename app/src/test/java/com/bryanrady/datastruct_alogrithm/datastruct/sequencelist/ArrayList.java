package com.bryanrady.datastruct_alogrithm.datastruct.sequencelist;

/**
 * 顺序表（ArrayList源码分析）
 * Created by bryanrady on 2017/12/7.
 *
 *
 *      extends AbstractList<E>
 *     implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 *
 */
public class ArrayList<E>{

    private Object[] elementData;
    private int size;

    public ArrayList(){
        this(10);
    }

    public ArrayList(int initialCapacity){
        super();
        if(initialCapacity<0){
            throw new IllegalArgumentException("Illegal Capacity:"+initialCapacity);
        }
        elementData = new Object[initialCapacity];
    }

}
