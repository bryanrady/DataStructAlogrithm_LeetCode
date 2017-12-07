package com.bryanrady.datastruct_alogrithm.datastruct.linkedlist;

import java.util.NoSuchElementException;

/**
 * Created by bryanrady on 2017/12/7.
 *  单链表的实现
 */

public class SinglyLinkedList<E>{

    private Node<E> first;      //单链表的头结点
    private Node<E> last;       //单链表的最后一个节点
    private int size;

    public SinglyLinkedList(){

    }

    public SinglyLinkedList(SinglyLinkedList c){
        this();
        addAll(c);
    }

    static class Node<E>{
        E data;     //数据域
        Node<E> next;   //指针域

        public Node(E data){
            this.data = data;
        }
    }

    /**
     * 返回单链表的长度
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 在单链表尾部添加一个元素节点
     * @param element
     */
    public void add(E element){
        Node<E> node = new Node<E>(element);
        Node<E> l = last;
        if(l == null){  //表明之前没有节点
            first = node;
        }else{
            l.next = node;
        }
        last = node;
        size++;
    }

    /**
     * 删除节点
     */
    public E remove(){
        return removeFirst();
    }

    /**
     * 删除第一个节点
     * @return
     */
    private E removeFirst(){
        Node<E> f = first;
        if(f == null){
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * 删除第一个节点
     * @param node
     * @return
     */
    private E unlinkFirst(Node<E> node) {
        E element = node.data;
        Node<E> next = node.next;
        node.data = null;
        node.next = null;
        first = next;
        if(next == null){
            last = null;
        }
        size--;
        return element;
    }

    /**
     * 在链表尾部添加一个集合
     * @param list
     * @return
     */
    public boolean addAll(SinglyLinkedList<E> list){
        return  addAll(size,list);
    }

    /**
     * 返回单链表的Object[]数组
     * @return
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        Node<E> e = first;
        while(e!=null){
            result[i++] = e.data;
            e = e.next;
        }
        return result;
    }

    /**
     * 在指定位置添加集合
     * @param index
     * @param list
     * @return
     */
    private boolean addAll(int index,SinglyLinkedList<E> list){
        if(index<0 || index > size){
            throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
        }
        Object[] a = list.toArray();
        int numNew = a.length;
        if(numNew == 0){
            return false;
        }
        for(Object o : a){
            E e = (E)o;
            Node<E> newNode = new Node<>(e);
            if(last==null){
                first = newNode;
            }else{
                last.next = newNode;
            }
            last = newNode;
        }
        size += numNew;
        return true;
    }

    /**
     * 根据传入的Index查找节点
     * @param index
     * @return
     */
    private Node<E> node(int index){
        Node<E> x = first;
        for(int i=0;i<index;i++){
           x = x.next;
        }
        return x;
    }

    public E get(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index:"+index);
        }
        return node(index).data;
    }


}
