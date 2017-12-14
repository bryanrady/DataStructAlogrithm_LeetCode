package com.bryanrady.datastruct_alogrithm.datastruct.hash;

/**
 * Created by bryanrady on 2017/12/14.
 */

import java.util.Map;

/**
 *     Android的SDK 中的HashMap可能和JDK中的有点小区别，但是基本一样，我们这里已学习jdk中的为主
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable

 http://blog.csdn.net/zhcswlp0625/article/details/65092032
 */
public class HashMap<K,V> {

    /**
     * 数据结构   在AndroidSDK里 这个 数据结构是隐藏的 @hide 默认维护的
     * @param <K>    Entry是一个单链表
     * @param <V>
     */
    static class Entry<K,V> implements Map.Entry<K,V>{
        K key;
        V value;
        Entry<K,V> next;
        int hash;

        Entry(int hash,K k,V v,Entry<K,V> n){
            this.hash = hash;
            this.key = k;
            this.value = v;
            this.next = n;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        /**
         * 比较两个Entry对象是否相等
         * @param o
         * @return
         */
        public final boolean equals(Object o){
            if(!(o instanceof Map.Entry)){
                return false;
            }
            Map.Entry e = (Map.Entry) o;
            Object k1 = getKey();   //本对象的key
            Object k2 = e.getKey(); //传进来对象的key
            if(k1 == k2 || (k1!=null && k1.equals(k2))){
                Object v1 = getValue();
                Object v2 = e.getValue();
                if(v1 == v2 || (v1!=null && v1.equals(v2))){
                    return true;
                }
            }
            return false;
        }

        public final int hashCode(){    //jdk上获取哈希值方法
            return (key==null   ? 0 : key.hashCode()) ^
                    (value==null ? 0 : value.hashCode());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        /**
         * 这个方法在key已经存在，且被重新赋值的时候调用
         * @param m
         */
        void recordAccess(HashMap<K,V> m){

        }

        /**
         * 每当从表中删除元素时，都会调用此方法
         * @param m
         */
        void recordRemoval(HashMap<K,V> m){

        }
    }


    /********     hashmap      ***********/

    /**默认初始容量为16**/
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /** 最大容量  2的30次方  整数最大值 **/
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**  默认的加载因子 75% **/
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry<K,V>[] table ;

    /**  HashMap的底层数组中已用槽的数量 **/
    int size;

    /**  实际的加载因子大小,先默认是这么多 **/
    final float loadFactor;

    /**  HashMap的阈值，用于判断是否需要调整HashMap的容量（threshold = 容量*加载因子） **/
    int threshold;

    /**
     * 带有容量、实际加载因子 的构造函数
     * @param initialCapacity
     * @param loadFactor
     */
    public HashMap(int initialCapacity,float loadFactor){
        if(initialCapacity < 0){
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        } else if (initialCapacity < DEFAULT_INITIAL_CAPACITY) {
            initialCapacity = DEFAULT_INITIAL_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor))     //加载因子不能小于0
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

        int capacity = 1;
        while(capacity < initialCapacity){
            capacity = capacity<< 1;    //2倍的增长
        }
        this.loadFactor = loadFactor;
        threshold = (int)(capacity * loadFactor);
        table = new Entry[capacity]; //初始化数组
        init();     //这个方法不做任何事，留给继承HashMap这个类的子类自己去实现
    }

    void init(){
    }

    /**
     * 只带容量的构造函数 实际上是选用了默认的加载因子
     * @param initialCapacity
     */
    public HashMap(int initialCapacity){
        this(initialCapacity,DEFAULT_LOAD_FACTOR);
    }

    /**
     * 无参构造函数  使用默认容量和默认的加载因子
     */
    public HashMap(){
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    /**
     * 包含子Map的构造函数
     * @param m
     */
    public HashMap(Map<? extends K,? extends V> m){
        this(Math.max((int)(m.size()/DEFAULT_LOAD_FACTOR) + 1, DEFAULT_INITIAL_CAPACITY),
                DEFAULT_LOAD_FACTOR);
        putAllForCreate(m);
    }

    /**
     * 将m中的全部元素逐个添加到HashMap中
     * @param m
     */
    private void putAllForCreate(Map<? extends K, ? extends V> m) {
        for(Map.Entry<? extends K,? extends V> map : m.entrySet()){
            putForCreate(map.getKey(),map.getValue());
        }
    }

    /**
     * 这里用来模拟散列函数求出hash值的过程，是在C层写的我们随便返回个0
     * @param key
     * @return
     */
    private int hash(Object key){
        return 0;
    }

    /**
     * 创建HashMap对应的“添加方法”,用来创建HashMap
     * @param key
     * @param value  sun.misc.Hashing.singleWordWangJenkinsHash(key)
     */
    private void putForCreate(K key, V value) {
        int hash = (key==null) ? 0 : hash(key);
        int i = indexFor(hash,table.length);
        for(Entry<K,V> e = table[i];e!=null;e = e.next){
            // 若该HashMap表中存在“键值等于key”的元素，则替换该元素的value值
            if(e.hash == hash && 
                    ( e.key== key || (key!=null && key.equals(e.key)))){
                e.value = value;
                return;
            }
        }
        createEntry(hash,key,value,i);
    }

    /**
     * 返回hash值在数组中的索引位置
     * @param h         哈希值
     * @param length    数组长度
     * @return
     */
    static int indexFor(int h, int length) {
        return h & (length-1);
    }

    /**
     *
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        //先写到这里  https://yq.aliyun.com/articles/72464
    }

    /**
     * 创建Entry。将“key-value”插入指定位置，bucketIndex是位置索引。
        它和addEntry的区别是：
          (1) addEntry()一般用在新增Entry可能导致“HashMap的实际容量”超过“阈值”的情况下。
                例如，我们新建一个HashMap，然后不断通过put()向HashMap中添加元素；put()是通过addEntry()新增Entry的。
               在这种情况下，我们不知道何时“HashMap的实际容量”会超过“阈值”；因此，需要调用addEntry().
      (02) createEntry() 一般用在 新增Entry不会导致“HashMap的实际容量”超过“阈值”的情况下。
          例如，我们调用HashMap“带有Map”的构造函数，它绘将Map的全部元素添加到HashMap中；但在添加之前，我们已经计算
          好“HashMap的容量和阈值”。也就是，可以确定“即使将Map中的全部元素添加到HashMap中，都不会超过HashMap的阈值”。
            此时，调用createEntry()即可。
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> e = table[bucketIndex];  // 保存“bucketIndex”位置的值到“e”中
        //设置“bucketIndex”位置的元素为“新Entry”，设置“e”为“新Entry的下一个节点”
        table[bucketIndex] = new Entry<>(hash,key,value,e);
        size++;
    }

}
