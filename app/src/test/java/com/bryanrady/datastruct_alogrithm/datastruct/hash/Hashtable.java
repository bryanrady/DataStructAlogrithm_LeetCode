package com.bryanrady.datastruct_alogrithm.datastruct.hash;

import java.util.Map;
import java.util.Objects;

/**
 * Created by bryanrady on 2017/12/19.
 *
 *  class Hashtable<K,V>
            extends Dictionary<K,V>
            implements Map<K,V>, Cloneable, java.io.Serializable
 */
public class Hashtable<K,V>{

    /**
     * 数据结构 单链表
     * @param <K>
     * @param <V>
     */
    static class HashtableEntry<K,V> implements Map.Entry<K,V>{

        int hash;
        K key;
        V value;
        HashtableEntry<K,V> next;

        protected HashtableEntry(int hash, K key, V value, HashtableEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            if(value == null) throw new NullPointerException();
            V oldValue = this.value;
            oldValue = value;
            return oldValue;
        }

        public boolean equals(Object obj) {
            if(!(obj instanceof Map.Entry))  return false;
            Map.Entry<?,?> e = (Map.Entry)obj;
            return key.equals(e.getKey()) && value.equals(e.getValue());
        }

        public int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public String toString() {
            return key.toString()+"="+value.toString();
        }

        //Hashtable 没有 recordAccess  recordRemoval 这两个方法
    }




    private HashtableEntry<K,V>[] table;

    private int count;

    private int threshold;

    private float loadFactor;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 获取key的hash值得方法
     * @param k
     * @return
     */
    private static int hash(Object k) {
        return k.hashCode();
    }

    /**
     * 带有初始容量和加载因子的构造函数
     * @param initialCapacity
     * @param loadFactor
     */
    public Hashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: "+loadFactor);

        if (initialCapacity==0)
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new HashtableEntry[initialCapacity];
        threshold = (initialCapacity <= MAX_ARRAY_SIZE + 1) ? initialCapacity : MAX_ARRAY_SIZE + 1;
    }

    /**
     * 只带有初始容量的构造函数也是默认加载因子是0.75f
     * @param initialCapacity
     */
    public Hashtable(int initialCapacity){
        this(initialCapacity,0.75f);
    }

    /**
     * 无参构造函数 默认初始容量是11个 加载因子0.75f
     */
    public Hashtable(){
        this(11,0.75f);
    //或    this(11);
    }

    public Hashtable(Map<? extends K,? extends V> m){
        this(Math.max(2*m.size(), 11), 0.75f);
        putAll(m);
    }

    //一些集合方法

    public synchronized int size() {
        return count;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized void putAll(Map<? extends K, ? extends V> t) {
        for (Map.Entry<? extends K, ? extends V> e : t.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * 添加方法
     * @param key
     * @param value
     */
    public synchronized V put(K key,V value){
        //首先确认这个value不是Null
        if(value == null) throw new NullPointerException();
        //确认这个key是否存在于hashtable中
        HashtableEntry[] tab = table;
        int hash = hash(key);
        //通过这个找到了这个key在数组中的下标
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for(HashtableEntry<K,V> e=tab[index];e!=null;e=e.next){
            if(e.hash == hash && e.key.equals(key)){
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        if(count >= threshold){ //如果键值对个数大于等于阈值
            rehash();

            tab = table;
            hash = hash(key);
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        //创建新的链表节点
        HashtableEntry<K,V> e = tab[index];
        tab[index] = new HashtableEntry<>(hash, key, value, e);
        count++;
        return null;
    }

    /**
     * 对原来的数组重新hash
     */
    protected void rehash(){
        int oldCapacity = table.length;
        HashtableEntry<K,V>[] oldMap = table;
        int newCapacity = (oldCapacity << 1) + 1;
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (oldCapacity == MAX_ARRAY_SIZE)
                // Keep running with MAX_ARRAY_SIZE buckets
                return;
            newCapacity = MAX_ARRAY_SIZE;
        }
        HashtableEntry<K,V>[] newMap = new HashtableEntry[newCapacity];
        threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);

        table = newMap;
        for (int i = oldCapacity ; i-- > 0 ;) {
            for (HashtableEntry<K,V> old = oldMap[i] ; old != null ; ) {
                HashtableEntry<K,V> e = old;
                old = old.next;

                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newMap[index];
                newMap[index] = e;
            }
        }
    }

    /**
     * 根据key删除Hashtable中的value
     * @param key
     * @return
     */
    public synchronized V remove(Object key) {
        HashtableEntry tab[] = table;
        int hash = hash(key);
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (HashtableEntry<K,V> e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                count--;
                V oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }

    public synchronized void clear() {
        HashtableEntry tab[] = table;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        count = 0;
    }

    public synchronized V get(Object key) {
        HashtableEntry tab[] = table;
        int hash = hash(key);
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (HashtableEntry<K,V> e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    public synchronized boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        HashtableEntry tab[] = table;
        for (int i = tab.length ; i-- > 0 ;) {
            for (HashtableEntry<K,V> e = tab[i] ; e != null ; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized boolean containsKey(Object key) {
        HashtableEntry tab[] = table;
        int hash = hash(key);
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (HashtableEntry<K,V> e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        return contains(value);
    }


    /***
     *      HashTable和HashMap区别
     *
     *          1、继承的父类不同
     *                  Hashtable继承自Dictionary类，而HashMap继承自AbstractMap类。但二者都实现了Map接口。
     *
     *          2、线程安全性不同
     *                   Hashtable 中的方法是Synchronize的，而HashMap中的方法在缺省情况下是非Synchronize的。在多线程并发的环境下，
     *                   可以直接使用Hashtable，不需要自己为它的方法实现同步，但使用HashMap时就必须要自己增加同步处理。
     *                   所以HashMap的效率高于HashTable.
     *
     *          3、是否提供contains方法
     *                   HashMap把Hashtable的contains方法去掉了，改成containsValue和containsKey，因为contains方法容易让人引起误解。
     *                   Hashtable则保留了contains，containsValue和containsKey三个方法，其中contains和containsValue功能相同。
     *
     *          4、key和value是否允许null值
     *                  其中key和value都是对象，并且不能包含重复key，但可以包含重复的value。
     *                  通过ContainsKey方法和ContainsValue的源码我们可以很明显的看出：
     *                       Hashtable中，key和value都不允许出现null值。
     *                       HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。
     *                       当get()方法返回null值时，可能是 HashMap中没有该键，也可能是该键所对应的值为null。
     *
     *           5、两个遍历方式的内部实现上不同
     *                 Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。
     *
     *          6、hash值不同
     *                   Hashtable计算hash值，直接用key的hashCode()，而HashMap重新计算了key的hash值
     *
     *          7、内部实现使用的数组初始化和扩容方式不同
     *                   HashTable在不指定容量的情况下的默认容量为11，而HashMap为16，SDK上是4。
     *                   Hashtable不要求底层数组的容量一定要为2的整数次幂，而HashMap则要求一定为2的整数次幂。
     *
     *                  Hashtable扩容时，将容量变为原来的2倍加1，而HashMap扩容时，将容量变为原来的2倍
     *
     *
     */
}
