package com.bryanrady.datastruct_alogrithm.datastruct.deque;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bryanrady on 2017/12/14.
 *
 *  阻塞双端队列  LinkedBlockingDeque是一个基于链表的双端阻塞队列。和LinkedBlockingQueue类似，
 *  区别在于该类实现了Deque接口，而LinkedBlockingQueue实现了Queue接口
 *
 从数据结构和功能需求上可以得到以下结论：

 1.要想支持阻塞功能，队列的容量一定是固定的，否则无法在入队的时候挂起线程。也就是capacity是final类型的。
 2.既然是双向链表，每一个结点就需要前后两个引用，这样才能将所有元素串联起来，支持双向遍历。也即需要prev/next两个引用。
 3.双向链表需要头尾同时操作，所以需要first/last两个节点，当然可以参考LinkedList那样采用一个节点的双向来完成，那样实现起来就稍微麻烦点。
 4.既然要支持阻塞功能，就需要锁和条件变量来挂起线程。这里使用一个锁两个条件变量来完成此功能。

    extends AbstractQueue<E>
    implements BlockingDeque<E>,  java.io.Serializable
 */

public class LinkedBlockingDeque<E> {

    /**
     * 阻塞式双端队列数据结构
     * @param <E>
     */
    static class Node<E>{
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E item){
            this.item = item;
        }
    }

    Node<E> first;
    Node<E> last;
    private int count;  //元素个数
    private final int capacity;   //队列容量一定是final类型的
    /** 元素进队列的锁 */
    private final ReentrantLock lock = new ReentrantLock();
    /** 队列为空时，notEmpty进入await状态，此时从队列取元素的操作阻塞着，知道其他地方往队列存如元素。存放时会调用signal */
    private final Condition notEmpty = lock.newCondition();
    /** 队列满时，notEmpty进入await状态，此时从队列存元素的操作阻塞着，直到其他地方从队列取元素。取元素时会调用signal */
    private final Condition notFull = lock.newCondition();

    public LinkedBlockingDeque(){
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    public LinkedBlockingDeque(Collection<? extends E> c){
        this(Integer.MAX_VALUE);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for(E e : c){
                if(e == null){
                    throw new NullPointerException();
                }
                if(!linkLast(new Node<E>(e))){  // 队列已满
                    throw new IllegalStateException("Deque full");
                }
            }
        }finally {
            lock.unlock();  //释放锁
        }
    }


    /********  基本链接和解除链接操作，仅在保持锁定时调用 *********/

    /**
     * 链接节点作为最后一个元素，如果已满则返回false。
     * @param node
     * @return
     */
    private boolean linkLast(Node<E> node) {
        if(count >= capacity){  //如果元素个数大于等于容量，则证明已满
            return false;
        }
        Node<E> l = last;
        node.prev = l;
        if(l==null){
            first = node;
            last = node;
        }else{
            l.next = node;
            last = node;
        }
        ++count;
        notEmpty.signal();  //在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()，
        return true;        // 传统线程的通信方式，Condition都可以实现，这里注意，
                            // Condition是被绑定到Lock上的，要创建一个Lock的Condition必须用newCondition()方法。
    }

    /**
     * 链接节点作为第一个元素，如果已满则返回false。
     * @param node
     * @return
     */
    private boolean linkFirst(Node<E> node){
        if(count >= capacity){
            return false;
        }
        Node<E> f = first;
        node.next = f;
        if(f == null){
            first = node;
            last = node;
        }else{
            first = node;
            f.prev = node;
        }
        ++count;
        notEmpty.signal();
        return true;
    }

    /**
     * 删除并返回第一个元素，如果为空则为null。
     * @return
     */
    private E unlinkFirst(){
        Node<E> f = first;
        if(f == null){
            return null;
        }
        // 获取第一个节点的值，并删除第一个节点，其中f.next=f这个操作很有java的特点。
        Node<E> n = f.next;
        E item = f.item;
        f.item = null;
        f.next = f; // help GC
        first = n;
        if(n == null){
            last = null;
        }else {
            n.prev = null;
        }
        --count;
        notFull.signal();   // 头节点删除成功，向在条件变量：notFull等待的线程发送信号，通知其中的一个线程
        return item;
    }

    /**
     * 删除并返回最后一个元素，如果为空则为null。
     * @return
     */
    private E unlinkLast(){
        Node<E> l = last;
        if(l == null){
            return null;
        }
        Node p = l.prev;
        E item = l.item;
        l.item = null;
        l.prev = l;     //help gc
        last = p;
        if(p == null){
            first = null;
        }else{
            p.next = null;
        }
        --count;
        notFull.signal();
        return item;
    }

    /********  BlockingDeque methods *********/

    /**
     * 在队头添加元素，如果双端队列是满的就抛异常
     * @param e
     */
    public void addFirst(E e){
        if(!offerFirst(e)){
            throw new IllegalStateException("Deque full");
        }
    }

    public void addLast(E e){
        if(!offerLast(e)){
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean offerFirst(E e){
        if(e == null){
            throw new NullPointerException();
        }
        Node node = new Node(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return linkFirst(node);
        }finally {
            lock.unlock();
        }
    }

    public boolean offerLast(E e){
        if(e == null){
            throw new NullPointerException();
        }
        Node node = new Node(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return linkLast(node);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 尾插入（队列先进先出），阻塞的方法
     * @param e
     * @throws InterruptedException
     */
    public void putFirst(E e) throws InterruptedException{
        if(e == null){
            throw new NullPointerException();
        }
        Node node = new Node(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (!linkFirst(node)){
                notFull.await(); // 在notFull条件上等待，直到被唤醒或中断
            }
        }finally {
            lock.unlock();
        }
    }

    public void putLast(E e) throws InterruptedException{
        if(e == null){
            throw new NullPointerException();
        }
        Node node = new Node(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (!linkLast(node)){
                notFull.await();
            }
        }finally {
            lock.unlock();
        }
    }

    public boolean offerFirst(E e,long timeout, TimeUnit unit) throws InterruptedException{
        if(e == null){
            throw new NullPointerException();
        }
        Node node = new Node(e);
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();   //可以被中断
        try {
            while (!linkFirst(node)){
                if(nanos <= 0L){
                    return false;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return true;
        }finally {
            lock.unlock();
        }
    }

    public boolean offerLast(E e,long timeout,TimeUnit unit) throws InterruptedException{
        if(e == null){
            throw new NullPointerException();
        }
        Node node = new Node(e);
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (!linkLast(node)){
                if(nanos <= 0L){
                    return false;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return true;
        }finally {
            lock.unlock();
        }
    }

    public E removeLast(){
        E x = pollLast();
        if(x == null){
            throw new NoSuchElementException();
        }
        return x;
    }

    public E removeFirst(){
        E x = pollFirst();
        if(x == null){
            throw new NoSuchElementException();
        }
        return x;
    }

    public E pollLast(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return unlinkLast();
        }finally {
            lock.unlock();
        }
    }

    public E pollFirst(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return unlinkFirst();
        }finally {
            lock.unlock();
        }
    }

    public E takeFirst() throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E x;
            while ((x =unlinkFirst()) == null){
                notEmpty.await();
            }
            return x;
        }finally {
            lock.unlock();
        }
    }

    public E takeLast() throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E x;
            while ((x=unlinkLast())==null){
                notEmpty.await();
            }
            return x;
        }finally {
            lock.unlock();
        }
    }

    public E pollLast(long timeout, TimeUnit unit) throws InterruptedException{
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            E x;
            while ( (x = unlinkLast()) == null) {
                if (nanos <= 0L)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return x;
        } finally {
            lock.unlock();
        }
    }

    public E pollFirst(long timeout, TimeUnit unit) throws InterruptedException{
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            E x;
            while ((x = unlinkFirst())==null){
                if(nanos <= 0L){
                    return null;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return x;
        }finally {
            lock.unlock();
        }
    }

    public E getFirst(){
        E x = peekFirst();
        if(x == null){
            throw new NoSuchElementException();
        }
        return x;
    }

    public E getLast(){
        E x = peekLast();
        if(x == null){
            throw new NoSuchElementException();
        }
        return x;
    }

    public E peekFirst(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (first==null) ? null : first.item;
        }finally {
            lock.unlock();
        }
    }

    public E peekLast(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (last==null) ? null : last.item;
        }finally {
            lock.unlock();
        }
    }

    /********  BlockingQueue methods *********/

    public boolean add(E e){
        addLast(e);
        return true;
    }

    public boolean offer(E e){
        return offerLast(e);
    }

    public void put(E e) throws InterruptedException{
        putLast(e);
    }

    public boolean offer(E e, long timeout, TimeUnit unit)
            throws InterruptedException {
        return offerLast(e, timeout, unit);
    }

    public E remove() {
        return removeFirst();
    }

    public E poll() {
        return pollFirst();
    }

    public E take() throws InterruptedException {
        return takeFirst();
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return pollFirst(timeout, unit);
    }
    public E element() {
        return getFirst();
    }

    public E peek() {
        return peekFirst();
    }

    /**
     * 返回理想情况下（没有内存和资源约束）此双端队列可不受阻塞地接受的额外元素数。
     * @return
     */
    public int remainingCapacity(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return capacity - count;
        }finally {
            lock.unlock();
        }
    }


    /********  Stack methods *********/

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        return removeFirst();
    }


    /********  Collection methods *********/

    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (Node<E> p = first; p != null; p = p.next)
                if (o.equals(p.item))
                    return true;
            return false;
        } finally {
            lock.unlock();
        }
    }

    public Object[] toArray() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] a = new Object[count];
            int k = 0;
            for (Node<E> p = first; p != null; p = p.next)
                a[k++] = p.item;
            return a;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (Node<E> f = first; f != null; ) {
                f.item = null;
                Node<E> n = f.next;
                f.prev = null;
                f.next = null;
                f = n;
            }
            first = last = null;
            count = 0;
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
