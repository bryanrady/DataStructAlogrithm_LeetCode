package com.bryanrady.datastruct_alogrithm.datastruct.deque;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by bryanrady on 2017/12/13.
 *
 *      class ArrayDeque<E> extends AbstractCollection<E>
                implements Deque<E>, Cloneable, Serializable

 */
public class ArrayDeque<E> extends AbstractCollection<E>{

    Object[] elements;
    int head;   //头指针，指向队列的头
    int tail;   //尾指针，指向队列的队尾

    private static final int MIN_INITIAL_CAPACITY = 8;  //最小容量

    /**
     * 构造函数1 空的构造函数默认创建大小为16的数组
     */
    public ArrayDeque(){
        elements = new Object[16];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 构造函数2  确认一个数组大小
     * @param numElements
     */
    public ArrayDeque(int numElements){
        allocateElements(numElements);
    }

    /**
     * 构造函数3
     * @param c
     */
    public ArrayDeque(Collection<? extends E> c){
        allocateElements(c.size());
        addAll(c);  //源码中是调用父类AbstractCollection的addAll()
    }

    /**
     * 分配空数组以保存给定数量的元素
     * @param numElements
     * 假设我们输入的是17，initialCapacity 被赋值为17，17的二进制为10001
    10001>>>1=1000，10001|1000=11001；
    11001>>>2 =110，11001|110=11111；
    11111>>>4=1，11111|1=11111;
    11111>>>8=0，11111|0=11111;
    11111>>>16=0，11111|0=11111;
    最后initialCapacity++，也就是11111+1=100000转化为10进制也就是32。32也确实是大于17最近的2的n次幂。
    这个算法的应用是很高效的，时间复杂度是O（1），要比用其他算法求效率要高。
     */
    private void allocateElements(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        if(numElements >= initialCapacity){
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;
            //如果数组容量太大溢出，直接赋值为2的30次幂
            if (initialCapacity < 0)    // Too many elements, must back off
                initialCapacity >>>= 1; // Good luck allocating 2^30 elements
        }
        elements = new Object[initialCapacity];
    }

    /**
     * 扩容
     */
    private void doubleCapacity(){
        assert head == tail;    //assert关键字 ，如果表达式为true则程序继续执行，否则程序抛出AssertionError，并终止执行。
        int p = head;
        int n = elements.length;
        int r = n - p; // 数组右面的长度
        int newCapacity = n << 1; //新容量是旧容量的2倍
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);  //拷贝数组右边，从head开始，拷贝r个长度，放在新数组从0开始的位置上。
        System.arraycopy(elements, 0, a, r, p);   //拷贝数组左边，从0开始，拷贝p个长度，放在新数组从r开始的位置上。
        elements = a;
        head = 0;  //head和tail指针重新初始化
        tail = n;
    }

    /**
     * 把一个元素插入到双端队列的队尾
     * @param e
     * @return
     */
    public boolean add(E e){
        addLast(e);
        return false;
    }

    /**
     * 把一个元素插入到双端队列的队尾
     * @param e
     * @return
     */
    public boolean offer(E e){
        return offerLast(e);
    }

    /**
     * 在双端队列队尾队尾添加一个元素
     * @param e
     */
    public void addLast(E e){
        if(e==null){
            throw new NullPointerException();
        }
        elements[tail] = e;  //需要注意的是，在队头插入结点是先走了一位在插入，这里队尾是现在当前位置插入，在“向前”走一位。
        if(tail== (tail+1) & (elements.length-1) == head){  //如果头尾相遇就扩容，这个算法上面已经介绍过了，
            doubleCapacity();                              // 和(elements.length - 1)进行与运算是为了尾指针能够自动循环。
        }
    }

    /**
     * 在双端队列队头添加一个元素
     * @param e
     */
    public void addFirst(E e){
        if(e==null){
            throw new NullPointerException();
        }
        elements[head=(head-1) & (elements.length-1)] = e;  //每次向头添加都是，头指针自减并与数组长度-1进行与运算。
        if(head==tail){  //如果循环数组加满，我们就扩容。
            doubleCapacity();
        }
    }

    public boolean offerFirst(E e){
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e){
        addLast(e);
        return true;
    }

    /**
     * 获取并移除此双端队列表示的队列的头部
     * @return
     */
    public E remove(){
        return removeFirst();
    }

    public E removeFirst(){
        E x = pollFirst();
        if(x == null){
            throw new NoSuchElementException();
        }
        return x;
    }

    /**
     * 获取并移除此双端队列表示的队列的尾部
     * @return
     */
    public E removeLast(){
        E x = pollLast();
        if(x == null){
            throw new NoSuchElementException();
        }
        return x;
    }

    /**
     * 获取并移除此双端队列表示的队列的头部（即此双端队列的第一个元素）；如果此双端队列为空，则返回 null。
     * @return
     */
    public E poll(){
        return pollFirst();
    }

    /**
     * 获取但不移除此双端队列表示的队列的头部。
     * @return
     */
    public E element(){
        return getFirst();
    }

    public E pollFirst(){
        Object[] elements = this.elements;
        int h = head;
        E result = (E)elements[h];
        //如果出队为空，则元素为null
        if(result != null){
            elements[h] = null; //把这个位置的元素置为Null
            head = (head+1) & (elements.length-1);  //头指针向后挪一位
        }
        return result;
    }

    public E pollLast(){
        Object[] objects = this.elements;
        int t = (tail - 1) & (elements.length - 1);  ;//因为队尾插入的特点，尾指针现在指向的是尾结点的后一位，所以要tail-1来指向当前的尾结点。
        E result = (E)elements[t];
        if(result != null){
            elements[t] = null;  //将取出来的尾结点的位置，置空。
            tail = t;
        }
        return result;
    }

    /**
     * 获取，但不移除此双端队列的第一个元素
     * @return
     */
    public E getFirst(){
        E result = (E)elements[head];
        if(result == null){
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * 获取，但不移除此双端队列的最后一个元素
     * @return
     */
    public E getLast(){
        E result = (E)elements[(tail-1) & (elements.length-1)];
        if(result == null){
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * 获取但不移除此双端队列表示的队列的头部（即此双端队列的第一个元素）；如果此双端队列为空，则返回 null。
     * @return
     */
    public E peek(){
        return peekFirst();
    }

    public E peekFirst(){
        return (E)elements[head];
    }

    public E peekLast(){
        return (E)elements[(tail-1) & (elements.length-1)];
    }

    /**********  用作栈的方法 ***************/

    /**
     * 将元素推入此双端队列表示的栈
     * @param e
     */
    public void push(E e){
        addFirst(e);
    }

    /**
     * 从此双端队列所表示的堆栈中弹出一个元素
     * @return
     */
    public E pop(){
        return removeFirst();
    }

}
