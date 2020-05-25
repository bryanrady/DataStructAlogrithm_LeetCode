package com.bryanrady.datastruct_alogrithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testToArray(){
//        SubClass[] subArray = {new SubClass(), new SubClass()};
//        //class [Lcom.bryanrady.datastruct_alogrithm.ExampleUnitTest$SubClass;
//        System.out.println(subArray.getClass());
//
//        //java对象的向上转型，子类数组是可以转换成父类数组的
//        BaseClass[] baseArray = subArray;
//        //class [Lcom.bryanrady.datastruct_alogrithm.ExampleUnitTest$SubClass;
//        System.out.println(subArray.getClass());
//
//        //java.lang.ArrayStoreException
//        //因为数组中的元素类型都是SubClass，所以会报错；也就是说假如我们有1个Object[]数组，并不代表
//        //着我们可以将Object对象存进去，这取决于数组中元素实际的类型
//        baseArray[0] = new BaseClass();

//        List<String> stringList = Arrays.asList("abc");
//        //class java.util.Arrays$ArrayList
//        System.out.println(stringList.getClass());
//
//        //返回的是String数组
//        Object[] objArray = stringList.toArray();
//        //class [Ljava.lang.String;
//        System.out.println(objArray.getClass());
//
//        //java.lang.ArrayStoreException
//        objArray[0] = new Object();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("123");
        arrayList.add("456");
        //class java.util.ArrayList
        System.out.println(arrayList.getClass());

        //返回的是Object数组
        Object[] objectArray = arrayList.toArray();
        //class [Ljava.lang.Object;
        System.out.println(objectArray.getClass());

        //我们可以将任意对象放到Object数组中
        objectArray[0] = 1;
        objectArray[0] = "abc";
        objectArray[0] = new Object();
    }

    static class BaseClass{

    }

    static class SubClass extends BaseClass{}


}