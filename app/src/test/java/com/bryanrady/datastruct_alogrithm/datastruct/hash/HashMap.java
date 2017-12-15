package com.bryanrady.datastruct_alogrithm.datastruct.hash;

/**
 * Created by bryanrady on 2017/12/14.
 */

import java.util.Map;

/**
 *     Android的SDK 中的HashMap可能和JDK中的有点小区别，但是基本一样，我们这里已学习jdk中的为主
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
 Hashmap继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口。它的key、value都可以为null，映射不是有序的。 　　 　　
 Hashmap不是同步的，如果想要线程安全的HashMap，可以通过Collections类的静态方法synchronizedMap获得线程安全的HashMap。

 在使用之前最好先估计好容量大小，这样就不用增大容量那么麻烦

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
        Entry<K,V> next;    //指向下一个节点
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
            //这个方法是为了有子类继承HashMap而开放的，如LinkedHashMap
        }

        /**
         * 每当从表中删除元素时，都会调用此方法
         * @param m
         */
        void recordRemoval(HashMap<K,V> m){
            //这个方法是为了有子类继承HashMap而开放的，在调用位置上
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
            if(e.hash == hash && ( e.key== key || (key!=null && key.equals(e.key)))){
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
     * 重新调整HashMap的大小，newCapacity是调整后的单位
     * @param newCapacity
     */
    void resize(int newCapacity){
        Entry<K,V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {  //如果原来的容量已经是最大容量，直接阈值设置为最大值
            threshold = Integer.MAX_VALUE;
            return;
        }

        // 新建一个HashMap，将“旧HashMap”的全部元素添加到“新HashMap”中，
        // 然后，将“新HashMap”赋值给“旧HashMap”。
        Entry<K,V>[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity*loadFactor);
    }

    /**
     * 将HashMap中原来的全部元素都添加到newTable中
     * @param newTable
     */
    void transfer(Entry[] newTable) {
        for(Entry<K,V> e : table){
            while (e != null){
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash,newTable.length);   //返回元素在新数组的下标
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    /**
     * 新增Entry。将“key-value”插入指定位置，bucketIndex是位置索引
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        // 若HashMap的实际大小 不小于 “阈值”，则调整HashMap的大小
        if(size++ >= threshold && table[bucketIndex] != null){
            resize(2*table.length);
            hash = (key != null) ? hash(key) : 0;
            bucketIndex = indexFor(hash,table.length);
        }
        createEntry(hash,key,value,bucketIndex);
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
        //设置“bucketIndex”位置的元素为“新Entry”，设置“e”为“新Entry的下一个节点”,总是让新的entry指向旧的entry
        table[bucketIndex] = new Entry<K,V>(hash,key,value,e);
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 将“key-value”添加到HashMap中
     * @param key
     * @param value
     * @return
     */
    public V put(K key,V value){
        if(key == null){
            return putForNullKey(value);
        }
        //计算key的hash值
        int hash = hash(key);
        int i = indexFor(hash,table.length);
        //先判断在table[i]的链表上有没有Key是key的节点，有的话就替换
        for(Entry<K,V> e = table[i];e!=null;e=e.next){
            if(hash==e.hash && (key==e.key || key.equals(e.key))){
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        //表示在链表上没有Key这个节点
        addEntry(hash,key,value,i);
        return null;
    }

    /**
     *  putForNullKey()的作用是将“key为null”键值对添加到table[0]位置
     * @param value
     * @return
     */
    private V putForNullKey(V value) {
        //这个0表示是key为null的时候，根据hash值0计算出来的在数组中的下标的位置0
        for(Entry<K,V> e = table[0];e!=null;e = e.next){
            if(e.key == null){
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);   //这个方法在key已经存在，value被重新赋值的时候调用(在HashMap上是空实现)
                return oldValue;
            }
        }
        //table[i]为空，这时直接生成一个新的entry放在table[i]上,hash值为0，下标也是0
        addEntry(0, null, value, 0);
        return null;
    }

    /**
     * 将一个map添加到HaspMap中
     * @param m
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();    //要添加的key的数量
        if(numKeysToBeAdded == 0){
            return;
        }
        /**
         * 如果要添加的集合数量大于或等于阈值，请展开映射。这是保守的；显而易见的条件是( m . size ( ) + size ) > =threshold，
         * 但如果要添加的键与此集合中已有的键重叠，则此条件可能导致集合具有两倍的相应容量。通过使用保守的计算，我们只需再调整一次大小。
         */
        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY)
                targetCapacity = MAXIMUM_CAPACITY;
            int newCapacity = table.length;
            while (newCapacity < targetCapacity)
                newCapacity <<= 1;  //2倍增长
            if (newCapacity > table.length)
                resize(newCapacity);  //重新调整HashMap的大小，newCapacity是调整后的单位
        }

        //返回一个Map.Entry实例化后Entry的对象集合
        for(Map.Entry<? extends K,? extends V> e : m.entrySet() ){
            put(e.getKey(),e.getValue());
        }
    }

    /**
     *  删除“键为key”元素
     * @param key
     * @return
     */
    public V remove(Object key){
        Entry<K,V> e = removeEntryForKey(key);
        return (e==null) ? null : e.value;
    }

    /**
     * 删除“键为key”元素
     * @param key
     * @return
     */
    private Entry<K,V> removeEntryForKey(Object key) {
        //计算这个key的hash值
        int hash = (key==null) ? 0 : hash(key);
        int i = indexFor(hash,table.length);
        Entry<K,V> prev = table[i]; //得到单链表的头节点
        Entry<K,V> e = prev;        //从头结点开始遍历

        //删除单链表中的节点
        while (e != null){
            Entry<K,V> next = e.next;
            if(hash==e.hash && (key==e.key || key.equals(e.key))){
                size--;
                if(prev == e){  //要删除的结点为头节点，将数组指向额链表的头结点设置为当前节点的下一个节点
                    table[i] = next;
                }else{
                    e.next = next;
                }
                e.recordRemoval(this);  //从数组中删除元素时调用
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }

    /**
     * 清空HashMap        蛮力法清空
     */
    public void clear(){
        Entry[] tab = table;
        for(int i=0;i<tab.length;i++){
            tab[i] = null;
        }
        size = 0;
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public V get(Object key){
        if(key == null){
            return getForNullKey(key);
        }
        Entry<K,V> e = getEntry(key);
        return (e==null) ? null : e.value;
    }

    /**
     *  获取“key为null”的元素的值,HashMap将“key为null”的元素存储在table[0]位置
     * @param key
     * @return
     */
    private V getForNullKey(Object key) {
        //从头遍历
        for(Entry<K,V> e=table[0]; e != null;e = e.next){
            if(e.key == null){
                return e.value;
            }
        }
        return null;
    }

    /**
     * 返回“键为key”的键值对
     * @param key
     * @return
     */
    final Entry<K,V> getEntry(Object key){
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash,table.length);
        for(Entry<K,V> e = table[i];e != null; e = e.next){
            if(hash==e.hash && (key==e.key || key.equals(e.key))){
                return e;
            }
        }
        return null;
    }

    /**
     * 判断是否包含这个key
     * @param key
     * @return
     */
    public boolean containsKey(Object key){
        return getEntry(key) != null;
    }

    /**
     * 判断是否包含这个 value值
     * @param value
     * @return
     */
    public boolean containsValue(Object value) {
        if (value == null)
            return containsNullValue();

        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    /**
     * 判断是否包含value是null的值
     * @return
     */
    private boolean containsNullValue() {
        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (e.value == null)
                    return true;
        return false;
    }




}
