package com.lym.basic;

import com.google.common.collect.Lists;
import com.google.common.primitives.Booleans;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class BooleansDemo {

    @Test
    public void testAsList() {
        boolean[] array = {true, false, false, true, false};
        List<Boolean> list = Booleans.asList(array);
        System.out.println(list);//[true, false, false, true, false]
    }

    @Test
    public void testToArray() {
        List<Boolean> list = Lists.newArrayList(true, false, false, true, false);
        boolean[] array = Booleans.toArray(list);
        System.out.println(Booleans.asList(array));//[true, false, false, true, false]
    }

    @Test
    public void testCompare() {
        boolean b1 = true;
        boolean b2 = false;
        int result = Booleans.compare(b1, b2);
        System.out.println(result);//1
    }

    @Test
    public void testConcat() {
        boolean[] array1 = {true, false, true};
        boolean[] array2 = {false, true, true};
        boolean[] result = Booleans.concat(array1, array2);
        System.out.println(Booleans.asList(result));//[true, false, true, false, true, true]
    }

    @Test
    public void testContains() {
        boolean[] array = {true, false, true};
        boolean target = false;
        boolean result = Booleans.contains(array, target);
        System.out.println(result);//true
    }

    @Test
    public void testCountTrue() {
        boolean[] array = {true, false, true};
        int count = Booleans.countTrue(array);
        System.out.println(count);//2
    }

    // 剩下的方法就不写了，基本数据类型都是一样的
}
