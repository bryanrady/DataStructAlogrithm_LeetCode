package com.bryanrady.datastruct_alogrithm.datastruct.list;

import java.util.Collection;

/**
 *  class LinkedList<E> extends AbstractSequentialList<E>
 *                     implements List<E>, Deque<E>, Cloneable, java.io.Serializable
 *
 *  LinkedList简介 LinkedList是基于双向循环链表（从源码中可以很容易看出）实现的，
 *  除了可以当作链表来操作外，它还可以当作栈，队列和双端队列来使用。
 *  LinkedList同样是非线程安全的，只在单线程下适合使用。
 *  LinkedList实现了Serializable接口，因此它支持序列化，能够通过序列化传输，
 *  实现了Cloneable接口，能被克隆。
 */

public class LinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList(){

    }

    public LinkedList(Collection<? extends E> c){
        this();
        addAll(c);
    }

    /**
     * 将集合c追加到LinkedList中
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends E> c){
        return addAll(size,c);
    }

    /**
     * 将集合c添加到LinkedList的指定位置
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index,Collection<? extends E> c){
        checkPositionIndex(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        if(numNew==0){
            return false;
        }
        Node<E> pred,succ;  //设置当前要插入节点的前后节点
        if(index==size){
            succ = null;
            pred = last;
        }else{
            succ = node(index);
            pred = succ.prev;
        }

        /****插入开始***/
        for(Object o : a){
            E e = (E)o;
            Node<E> newNode = new Node<>(pred,e,null);
            if(pred==null){
                first = newNode;
            }else{
                pred.next = newNode;
            }
            //此时把要插入的节点的上一个节点变成刚刚插入的节点
            pred = newNode;
        }
        /****插入完成***/

        //设置前后节点的关系
        if(succ == null){
            last = pred;
        }else{
            pred.next = succ;
            succ.prev = pred;
        }
        size += numNew;
        return true;
    }

    /**
     * 添加元素
     * @param e
     * @return
     */
    public boolean add(E e){
        linkLast(e);
        return true;
    }

    /**
     * 在链表头部添加元素e,并将元素e作为第一个元素
     * @param e
     */
    private void linkFirst(E e){
        Node<E> f = first;
        Node<E> newNode = new Node<>(null,e,f);
        first = newNode;
        if(f==null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 在链表尾部添加元素e,并将e作为最后一个元素
     * @param e
     */
    private void linkLast(E e){
        Node<E> l = last;
        Node newNode = new Node(l,e,null);
        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
            newNode.prev = l;
        }
        last = newNode;
        size++;
    }

    /**
     * 在非空节点succ之前插入元素e
     * @param e
     * @param succ
     */
    private void linkBefore(E e,Node<E> succ){
        Node<E> pred = succ.prev;
        Node<E> newNode = new Node<>(pred,e,succ);
        if(pred == null){
            first = newNode;
        }else{
            pred.next = newNode;
        }
        succ.prev = newNode;
        size++;
    }

    //unlink准备

    /**
     * 根据index位置获取节点
     *  利用双向链表的特点提高查找效率
     * @param index
     * @return
     */
    private Node<E> node(int index){
        if(index < (size>>1)){  //说明要查找的节点在前半部分
            Node<E> x = first;
            for(int i=0;i<index;i++){
                x = x.next;
            }
            return x;
        }else{  //说明要查找的节点在前半部分
            Node<E> x = last;
            for(int i=size-1;i>index;i--){
                x = x.prev;
            }
            return x;
        }
    }

    /**
     *  添加元素的时候检查索引位置
     * @param index
     */
    private void checkPositionIndex(int index){
        if(index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
        }
    }

    /**
     * 返回LinkedList的Object数组
     * @return
     */
    public Object[] toArray(){
        Object[] o = new Object[size];
        int i=0;
        for(Node<E> x=first;x!=null;x=x.next){
            o[i++] = x.element;
        }
        return o;
    }

    static class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev,E element,Node next){
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

}
