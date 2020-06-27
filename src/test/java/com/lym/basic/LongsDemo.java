package com.lym.basic;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Longs;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class LongsDemo {

    @Test
    public void testAsList() {
        List<Long> list = Longs.asList(1, 2, 3);
        System.out.println(list);//[1, 2, 3]
    }

    @Test
    public void testCompare() {
        long l1 = 1L;
        long l2 = 2L;
        int result = Longs.compare(l1, l2);
        System.out.println(result);//-1
    }

    @Test
    public void testConcat() {
        long[] ls1 = {1L, 2L, 3L};
        long[] ls2 = {4L, 5L, 6L};
        long[] result = Longs.concat(ls1, ls2);
        System.out.println(Longs.asList(result));//[1, 2, 3, 4, 5, 6]
    }

    @Test
    public void testContains() {
        long[] array = {1L, 2L, 3L};
        long target = 3L;
        boolean result = Longs.contains(array, target);
        System.out.println(result);//true
    }

    @Test
    public void testIndexOf() {
        long[] array = {1L, 2L, 3L, 4L ,5L};
        long target = 4L;
        int index = Longs.indexOf(array, target);
        System.out.println(index);//3
    }

    @Test
    public void testIndeOf2() {
        long[] array = {1L, 2L, 3L, 4L ,5L};
        long[] target = {4L, 5L};
        int index = Longs.indexOf(array, target);
        System.out.println(index);//3
    }

    @Test
    public void testJoin() {
        long[] array = {1L, 2L, 3L, 4L ,5L};
        String result = Longs.join(",", array);
        System.out.println(result);//1,2,3,4,5
    }

    @Test
    public void testMax() {
        long[] array = {1L, 2L, 3L, 4L ,5L};
        long max = Longs.max(array);
        System.out.println(max);//5
    }

    @Test
    public void testMin() {
        long[] array = {1L, 2L, 3L, 4L ,5L};
        long min = Longs.min(array);
        System.out.println(min);// 1L
    }

    @Test
    public void testStringConverter() {
        Converter<String, Long> converter = Longs.stringConverter();
        long result = converter.convert("1112");
        System.out.println(result);//1112
    }

    @Test
    public void testToArray() {
        List<Long> list = Lists.newArrayList(1L, 2L, 5L, 2L, 4L, 3L);
        long[] result = Longs.toArray(list);
        System.out.println(Longs.asList(result));//[1, 2, 5, 2, 4, 3]
    }

    @Test
    public void testToByteArray() {
        byte[] bs = Longs.toByteArray(10L);
        System.out.println(Bytes.asList(bs));//[0, 0, 0, 0, 0, 0, 0, 10]
    }

    @Test
    public void testTryParse() {
        long result = Longs.tryParse("1010");
        System.out.println(result);//1010
    }
}
