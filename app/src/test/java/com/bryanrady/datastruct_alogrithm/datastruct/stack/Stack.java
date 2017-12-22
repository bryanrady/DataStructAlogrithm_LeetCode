package com.bryanrady.datastruct_alogrithm.datastruct.stack;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * Created by bryanrady on 2017/12/22.
 *
 *  数据结构栈
 */

public class Stack<E> extends Vector<E> {

    public Stack(){

    }

    /**
     * 将一个元素添加到栈顶 也就是表尾中,并返回添加的元素
     * @param item
     * @return
     */
    public E push(E item){
        addElement(item);
        return item;
    }

    /**
     * 取出栈顶的元素，并删除这个元素
     * @return
     */
    public synchronized E pop(){
        int length = size();
        E element = peek();
        removeElementAt(length-1);
        return element;
    }

    /**
     * 取出栈顶的元素，不删除
     * @return
     */
    public synchronized E peek(){
        int length = size();
        if(length == 0){
            throw new EmptyStackException();
        }
        return elementAt(length-1);
    }

    public boolean empty(){
        return size()==0;
    }

    /**
     * 从栈中查找对象obj第一次出现的位置  也就是在表尾最后一次出现的位置
     * @param obj
     * @return
     */
    public synchronized int seach(Object obj){
        int index = lastIndexOf(obj);
        if(index > 0){
            return size() - index;
        }
        return -1;
    }


}
