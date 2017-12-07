package com.bryanrady.datastruct_alogrithm.datastruct.list;

import java.util.Arrays;
import java.util.Collection;

/**
 * 顺序表（ArrayList源码剖析）
 *      ArrayList<E> extends AbstractList<E>
 *     implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 *
 *     ArrayList是基于数组实现的，所以里面有很多操作都是通过数组来操作的，它是一个动态数组，
 *     其容量能自动增长，类似于C语言中的动态申请内存，动态增长内存。ArrayList不是线程安全的，
 *     只能在单线程环境下，多线程环境下可以考虑用collections.synchronizedList(List l)函数
 *     返回一个线程安全的ArrayList类，也可以使用concurrent并发包下的CopyOnWriteArrayList类。
 *     ArrayList实现了Serializable接口，因此它支持序列化，能够通过序列化传输，实现了RandomAccess
 *     接口，支持快速随机访问，实际上就是通过下标序号进行快速访问，实现了Cloneable接口，能被克隆。
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

    public ArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        size = elementData.length;
        if(elementData.getClass() != Object[].class){
            elementData = Arrays.copyOf(elementData,size,Object[].class);
        }
    }

    /**
     * 将当前容量值设置为实际元素个数
     */
    public void trimToSize(){
        int oldCapacity = elementData.length;
        if(size < oldCapacity){
            elementData = Arrays.copyOf(elementData,size);
        }
    }

    /**
     * 返回集合的大小
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 判断集合是否为null
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 清空集合中的元素
     */
    public void clear(){
        if(size > 0){
            for(int i=0;i<size;i++){
                elementData[i] = null;
            }
        }
        size = 0;
    }

    /**
     * 返回集合的Object数组
     * @return
     */
    public Object[] toArray(){
        return Arrays.copyOf(elementData,size);
    }

    /**
     * 往集合里面添加元素
     * @param e
     * @return
     */
    public boolean add(E e){
        ensureCapacity(size+1);
        elementData[size++] = e;
        return true;
    }

    /**
     * 在指定的位置添加元素
     * @param index
     * @param e
     */
    public void add(int index,E e){
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        //将index位置（包含）之后的数组全部往右移动一位
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = e;
        size++;
    }

    /**
     * 将集合c追加到ArrayList中
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends E> c){
        Object[] o = c.toArray();
        int numNew = o.length;
        ensureCapacity(size+numNew);
        System.arraycopy(o,0,elementData,size,numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 将集合c添加到ArrayList中的指定位置
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index,Collection<? extends E> c){
        rangeCheckForAdd(index);
        Object[] o = c.toArray();
        int numNew = o.length;
        int numMoved = size - index;
        if(numMoved>0){
            System.arraycopy(elementData,index,elementData,index+numNew,numMoved);
        }
        System.arraycopy(o,0,elementData,index,numNew);
        size += numNew;
        return numMoved != 0;
    }

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);
        E oldValue = (E)elementData[index];
        //需要复制元素的个数，也就是index后面的元素个数
        int numMoved = size - index -1;
        if(numMoved>0){
            //将index后面的元素全部往前移动1个位置
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        //经过arraycopy的移位，数组容器的最个位置被腾空，但是仍然持有某个对象的引用，需要把这个多余的引用置为null.
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 删除对象
     * @param o
     */
    public boolean remove(Object o){
        if(o == null){
            for(int index=0;index<size;index++){
                fastRemove(index);
                return true;
            }
        }else{
            for(int index=0;index<size;index++){
                if(elementData[index].equals(o)){
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据Index位置快速删除元素
     */
    private void fastRemove(int index){
        int numMoved = size - index -1;
        if(numMoved > 0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
    }

    /**
     * 将index位置的元素设置为e
     * @param index
     * @param e
     * @return
     */
    public E set(int index,E e){
        rangeCheck(index);
        E oldValue = (E)elementData[index];
        elementData[index] = e;
        return oldValue;
    }

    /**
     * 根据Index的位置返回元素
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);
        E oldValue = (E)elementData[index];
        return oldValue;
    }

    /**
     * 查看集合中是否包含o这个对象
     * @param o
     * @return
     */
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    /**
     * 返回集合中的对象o中的索引位置
     * @param o
     * @return
     */
    public int indexOf(Object o){
        if(o == null){
            for(int i=0;i<size;i++){
                if(elementData[i] == null){
                    return i;
                }
            }
        }else{
            for(int i=0;i<size;i++){
                if(elementData[i].equals(o)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回集合中对象o最后出现的索引位置
     * @param o
     * @return
     */
    public int lastIndexOf(Object o){
        if(o == null){
            for(int i=size-1;i>=0;i--){
                if(elementData[i] == null){
                    return i;
                }
            }
        }else{
            for(int i=size-1;i>=0;i--){
                if(elementData[i].equals(o)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 检查index的范围，判断有没有越界
     * @param index
     */
    private void rangeCheck(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
    }

    /**
     * 在指定位置添加元素时要检查index
     * @param index
     */
    private void rangeCheckForAdd(int index){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
    }

    /**
     * 先确保集合的容量
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity){
        int oldCapacity = elementData.length;
        if(minCapacity>oldCapacity){    //说明当前容量不够用，需要扩容
            grow(minCapacity);
        }
    }

    /**
     * 容量增长
     * @param minCapacity
     */
    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity>>1; //增长到原来的1.5倍
        if(newCapacity < minCapacity){     //如果还不够，直接将当前容量设置为新的容量
            newCapacity = minCapacity;
        }
        if(newCapacity > Integer.MAX_VALUE - 8){
            hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData,newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > Integer.MAX_VALUE - 8) ? Integer.MAX_VALUE : Integer.MAX_VALUE - 8;
    }


}
