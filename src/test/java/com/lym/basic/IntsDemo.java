package com.lym.basic;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class IntsDemo {

    @Test
    public void testAsList() {
        List<Integer> list = Ints.asList(1, 2, 3, 4, 5);
        System.out.println(list);//[1, 2, 3, 4, 5]
    }

    @Test
    public void testToArray() {
        List<Integer> a = Lists.newArrayList(1, 2, 3, 4, 5);
        int[] result = Ints.toArray(a);
    }

    /**
     * 解析指定的字符串作为符号十进制整数
     */
    @Test
    public void testTryParse() {
        int result = Ints.tryParse("101");
        System.out.println(result);//101
    }

    @Test
    public void testCheckedCast() {
        long l = 100392L;
        int i = Ints.checkedCast(l);
        System.out.println(i);//100392
    }

    @Test
    public void testCompare() {
        int a = 10003;
        int b = 10002;
        int result = Ints.compare(a, b);//a>b=1,a==b=0,a<b=-1
        System.out.println(result);//1
    }

    @Test
    public void testConcat() {
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        int[] c = {7, 8, 9};
        int[] result = Ints.concat(a, b, c);
        System.out.println(Ints.asList(result));//[1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    @Test
    public void testJoin() {
        String result = Ints.join(",", 1, 2, 3, 4, 5);
        System.out.println(result);//1,2,3,4,5
    }

    @Test
    public void testIndexOf() {
        int[] a = {1, 3, 6, 8, 9};
        int index = Ints.indexOf(a, 6);
        System.out.println(index);//2
    }

    @Test
    public void testIndexOf2() {
        int[] a = {1, 3, 6, 8, 9};
        int[] b = {6, 8};
        int index = Ints.indexOf(a, b);
        System.out.println(index);//2
    }

    @Test
    public void testLastIndexOf() {
        int[] a = {1, 3, 6, 3, 9};
        int index = Ints.lastIndexOf(a, 3);
        System.out.println(index);//3
    }

    @Test
    public void testMax() {
        int[] a = {1, 3, 6, 3, 9, 7};
        int result = Ints.max(a);
        System.out.println(result);//9
    }

    @Test
    public void testMin() {
        int[] a = {1, 3, 6, 3, 9, 7};
        int result = Ints.min(a);
        System.out.println(result);//1
    }

    /**
     * 返回最接近的int值
     */
    @Test
    public void testSaturatedCast() {
        long l = 1000000000000000001L;
        int result = Ints.saturatedCast(l);
        System.out.println(result);//2147483647
    }

    @Test
    public void testStringConverter() {
        Converter<String, Integer> converter = Ints.stringConverter();
        int result = converter.convert("8");
        System.out.println(result);//8
    }

}
