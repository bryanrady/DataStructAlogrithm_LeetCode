package com.bryanrady.datastruct_alogrithm.datastruct.queueAnddeque;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * Created by bryanrady on 2017/12/22.
 *
 * 优先级队列  如果要考虑线程安全的话，可以用并发包里的PriorityBlockingQueue
 *
 *      extends AbstractQueue<E>
 *               implements java.io.Serializable
 *
 *     AbstractQueue<E> extends AbstractCollection<E>    AbstractQueue是AbstractCollection的一个子类
 *         implements Queue<E>
 *
 *             优先级队列里的数据是有序的，出队入队、添加删除 就是重新对完全二叉树重新建堆的过程。
 *
 *
 */

public class PriorityQueue<E> {


    //默认初始容量
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    Object[] queue;     //基于数组实现，是顺序存储结构

    int size;

    //比较器，如果优先级队列使用元素的自然排序，则为null。
    private final Comparator<? super E> comparator;

    public PriorityQueue(){
        this(DEFAULT_INITIAL_CAPACITY,null);
    }

    public PriorityQueue(int initialCapacity){
        this(initialCapacity,null);
    }

    //使用比较器就必须实现带有比较器的构造函数

    /**
     * 使用默认的初始容量创建PriorityQueue,并且根据指定的比较器对其元素进行排序
     * @param comparator
     */
    public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    /**
     *  使用指定的初始容量创建PriorityQueue,并且根据指定的比较器对其元素进行排序
     * @param initialCapacity
     * @param comparator
     */
    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        //注意:实际上并不需要对至少一个进行这种限制，而是为了jdk1.5兼容性而继续进行
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    /**
     * 创建一个PriorityQueue，其中包含指定集合中的元素。
     * @param c
     */
    public PriorityQueue(Collection<? extends E> c){
        if (c instanceof SortedSet<?>) {    //如果指定的集合是SortedSet的实例，则根据SortedSet的比较器对该优先级队列进行排序。
            SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
            this.comparator = (Comparator<? super E>) ss.comparator();
            initElementsFromCollection(ss);

        }else if (c instanceof PriorityQueue<?>) {    //PriorityQueue，也根据PriorityQueue自己的比较器对该优先级队列进行排序。
            PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
            this.comparator = (Comparator<? super E>) pq.comparator();
            initFromPriorityQueue(pq);

        }else{  //否则，此优先级队列将根据其元素的顺序排列。
            this.comparator = null;
            initFromCollection(c);
        }
    }

    public PriorityQueue(PriorityQueue<? extends E> c) {
        this.comparator = (Comparator<? super E>) c.comparator();
        initFromPriorityQueue(c);
    }

    public PriorityQueue(SortedSet<? extends E> c) {
        this.comparator = (Comparator<? super E>) c.comparator();
        initElementsFromCollection(c);
    }

    private void initFromPriorityQueue(PriorityQueue<? extends E> c) {
        if (c.getClass() == PriorityQueue.class) {  //判断要添加的集合队列c是不是PriorityQueue的实例
            Object[] a = c.toArray();
            this.queue = a;
            this.size = a.length;
        }else{
        //    initFromCollection(c);
        }
    }

    private void initFromCollection(Collection<? extends E> c) {
    //    initElementsFromCollection(c);
        heapify();
    }

    /**
     * 在树上建堆
     *
     *  从最后一个飞叶子节点开始，一直到二叉树的根节点开始建堆
     */
    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) queue[i]);
    }

    /**
     *  相当于构建小顶堆过程
     * 在要填充的位置k上添加元素x ,直到它小于或等于其子节点或为叶子节点
     * @param k
     * @param x
     */
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    /**
     *  根据自己的比较规则进行比较，然后在树上创建推
     * @param k
     * @param x
     */
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {  //一直建堆，直到不是叶子节点
            int child = (k << 1) + 1; // 假设左孩子是最小比父节点和右孩子都小
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    /**
     * 使用现有的比较器进行比较，然后在树上建堆
     * @param k
     * @param x
     */
    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, (E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    /**
     * 把集合中的元素放到队列中
     * @param c
     */
    private void initElementsFromCollection(SortedSet<? extends E> c) {
        Object[] a = c.toArray();
        //如果a的对象实例和数组的实例不相等，那我们就复制这个数组a
        if(a.getClass() != Object[].class){
            a = Arrays.copyOf(a, a.length, Object[].class);
        }
        int len = a.length;
        if (len == 1 || this.comparator != null)
            for (Object e : a){
                if (e == null){
                    throw new NullPointerException();
                }
            }

        this.queue = a;
        this.size = a.length;
    }


    //上面都是构造函数之间扯来扯去的方法， 最主要的就是看到了 一个在树上建堆的 方法， 用来排序


    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 扩容
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));    //增长了一半
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }


    //集合的方法，和队列的操作

    /**
     * 添加一个元素到表尾
     * @param e
     * @return
     */
    public boolean add(E e) {
        return offer(e);
    }

    /**
     * 将指定的元素插入此优先级队列 的队尾
     * @param e
     * @return
     */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }

    /**
     *  和stack一样道理，只查询不删除
     * @return
     */
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equals(queue[i]))
                    return i;
        }
        return -1;
    }

    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1)
            return false;
        else {
            removeAt(i);
            return true;
        }
    }

    E removeAt(int i) {
        int s = --size;
        if (s == i) // 相当于删除最后一个元素
            queue[i] = null;
        else {
            E moved = (E) queue[s];
            queue[s] = null;
            siftDown(i, moved);
            if (queue[i] == moved) {
                siftUp(i, moved);
                if (queue[i] != moved)
                    return moved;
            }
        }
        return null;
    }

    boolean removeEq(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == queue[i]) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(queue, size);
    }

    //一些辅助性的方法

    public Comparator<? super E> comparator() {
        return comparator;
    }

    /**
     * 筛选       也就是构建小顶堆的过程
     * 在位置k插入元素x，通过以下操作保持堆不变（也就是建堆的过程），在树中插入元素x，直到它大于或等于其父节点，或为根节点
     * @param k
     * @param x
     */
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
}
