package com.bryanrady.datastruct_alogrithm.datastruct.list;

import java.util.Collection;
import java.util.NoSuchElementException;

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
     * 数据结构
     * @param <E>
     */
    static class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev,E element,Node next){
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 返回LinkedList的大小
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 判断集合是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
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
            //此时把下一个要插入的节点的上一个节点变成刚刚插入的节点
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
     * 在指定的位置往集合中添加元素e
     * @param index
     * @param e
     */
    public void add(int index,E e){
        checkPositionIndex(index);
        if(index == size){
            linkLast(e);
        }else{
            linkBefore(e,node(index));
        }
    }

    /**
     * 添加元素作为第一个节点
     * @param e
     */
    public void addFirst(E e){
        linkFirst(e);
    }

    /**
     * 添加元素作为最后一个节点
     * @param e
     */
    public void addLast(E e){
        linkLast(e);
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

    /**
     * 删除元素，默认删除第一个,并返回
     * @return
     */
    public E remove(){
        return removeFirst();
    }

    /**
     * 删除集合中指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 在集合中删除指定的对象
     * @param o
     * @return
     */
    public boolean remove(Object o){
        if(o == null){
            for(Node<E> x=first;x!=null;x=x.next){
                if(x.element == null){
                    unlink(x);
                    return true;
                }
            }
        }else{
            for(Node<E> x=first;x!=null;x=x.next){
                if(x.element.equals(o)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除链表的第一个元素并返回
     * @return
     */
    public E removeFirst(){
        Node<E> f = first;
        if(f == null){
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * 删除链表的最后一个元素，并返回
     * @return
     */
    public E removeLast(){
        Node<E> l = last;
        if(l == null){
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    /**
     * 清空链表     循环遍历全部置为null
     */
    public void clear(){
        for(Node<E> x=first;x!=null;x=x.next){
            x.element =null;
            x.prev = null;
            x.next = null;
            x = null;
        }
        first = last = null;
        size = 0;
    }

    /**
     * 删除一个非空节点x，并返回
     * @param x
     * @return
     */
    private E unlink(Node<E> x){
        E element = x.element;
        Node<E> pred = x.prev;
        Node<E> succ = x.next;
        if(pred == null){
            first = succ;
        }else{
            pred.next = succ;
            x.prev = null;
        }
        if(succ == null){
            last = pred;
        }else{
            succ.prev = pred;
            x.next = null;
        }
        x.element = null;
        size--;
        return element;
    }

    /**
     * 删除不为空的第一个节点
     * @param f
     */
    private E unlinkFirst(Node<E> f){
        E element = f.element;
        Node<E> succ = f.next;
        f.element = null;     //help gc
        f.next = null;
        first = succ;
        if(succ == null){
            last = null;
        }else{
            succ.prev = null;
        }
        size--;
        return element;
    }

    /**
     * 删除不为空的最后一个节点
     * @param l
     * @return
     */
    private E unlinkLast(Node<E> l){
        E element = l.element;
        Node<E> pred = l.prev;
        l.element = null;
        l.prev = null;
        last = pred;
        if(pred == null){
            first = null;
        }else{
            pred.next = null;
        }
        size--;
        return element;
    }

    /**
     * 修改在链表中指定位置的元素
     * @param index
     * @param e
     * @return
     */
    public E set(int index,E e){
        checkElementIndex(index);
        Node<E> oldNode = node(index);
        E oldVal = oldNode.element;
        oldNode.element = e;
        return oldVal;
    }

    /**
     * 根据index获取集合中的指定的元素
     * @param index
     * @return
     */
    public E get(int index){
        checkElementIndex(index);
        return node(index).element;
    }

    /**
     * 获取第一个节点上的元素
     * @return
     */
    public E getFirst(){
        Node<E> f = first;
        if(f == null){
            throw new NoSuchElementException();
        }
        return f.element;
    }

    /**
     * 获取最后一个节点的元素
     * @return
     */
    public E getLast(){
        Node<E> l = last;
        if(l == null){
            throw new NoSuchElementException();
        }
        return l.element;
    }

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
     * 判断链表集合中是否包含此对象
     * @param o
     * @return
     */
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    /**
     * 获取对象o在链表集合中的索引位置
     * @param o
     * @return
     */
    public int indexOf(Object o){
        int index = 0;
        if(o == null){
            for(Node<E> x=first;x!=null;x=x.next){
                if(x.element == null){
                    return index;
                }
                index++;
            }
        }else{
            for(Node<E> x=first;x!=null;x=x.next){
                if(x.element.equals(o)){
                   return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * 返回此对象在链表中最后出现的位置
     * @param o
     * @return
     */
    public int lastIndexOf(Object o){
        int index = size-1;
        if(o == null){
            for(Node<E> x=last;x!=null;x=x.prev){
                if(x.element == null){
                    return index;
                }
                index--;
            }
        }else{
            for(Node<E> x=last;x!=null;x=x.prev){
                if(x.element.equals(o)){
                    return index;
                }
                index--;
            }
        }
        return -1;
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
     * 在删除和获取元素时判断检查下标位置
     * @param index
     */
    private void checkElementIndex(int index){
        if(index<0 || index>=size) {
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

    /***    LinkedList实现了Deque<E>接口，Deque<E>接口对Queue<E>接口进行了扩展</>
     * </>  LinkedList作为队列使用
     *      一般用LinkedList作为队列使用较多，使用栈的时候最好还是使用Stack<E>这个类
     * </>*****/

    /**
     * 往队列尾部添加元素
     * @param e
     * @return
     */
    public boolean offer(E e){
        return add(e);
    }

    /**
     * 从队列头部取元素,并删除
     * @return
     */
    public E poll(){
        Node<E> f = first;
        if(f == null){
            return null;
        }
        return unlinkFirst(f);
    }

    /**
     * 从队列头部读取元素，但是不删除
     * @return
     */
    public E peek(){
        Node<E> f = first;
        if(f == null){
            return null;
        }
        return f.element;
    }

    public boolean offerFrist(E e){
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e){
        addLast(e);
        return true;
    }

    public E pollFirst(){
        Node<E> f = first;
        if(f == null){
            return null;
        }
        return unlinkFirst(f);
    }

    public E pollLast(){
        Node<E> f = first;
        if(f == null){
            return null;
        }
        return unlinkLast(f);
    }

    public E peekFirst(){
        Node<E> f = first;
        if(f == null){
            return null;
        }
        return f.element;
    }

    public E peekLast(){
        Node<E> l = last;
        if(l == null){
            return null;
        }
        return l.element;
    }

    /***    LinkedList还可以当成是栈来使用  *****/

    /**
     * 往栈顶添加元素,就是在链表前面添加元素作为第一个节点
     * @param e
     */
    public void push(E e){
        addFirst(e);
    }

    /**
     * 往栈顶取元素并删除，即删除链表前面删除元素，这样在就满足了栈的先进后出的原则
     *  还有个不删除的就是和peek（）方法一样
     * @return
     */
    public E pop(){
        return removeFirst();
    }

}
