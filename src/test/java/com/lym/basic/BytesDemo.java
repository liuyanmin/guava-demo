package com.lym.basic;

import com.google.common.primitives.Bytes;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class BytesDemo {

    /**
     * 1、byte[] 转 List<Byte>
     */
    @Test
    public void testAsList() {
        byte b1 = 1, b2 = 2, b3 = 3, b4 = 4, b5 = 5;
        List<Byte> bytes = Bytes.asList(b1, b2, b3, b4, b5);
        System.out.println(bytes);//[1, 2, 3, 4, 5]
    }

    /**
     * 2、List<Byte> 转 byte[]
     */
    @Test
    public void testToArray() {
        byte b1 = 1, b2 = 2, b3 = 3, b4 = 4, b5 = 5;
        List<Byte> bytes = Bytes.asList(b1, b2, b3, b4, b5);
        byte[] result = Bytes.toArray(bytes);
    }

    /**
     * 3、多个集合组合成一个集合
     */
    @Test
    public void testConcat() {
        byte[] bs1 = {1, 2, 3, 4, 5};
        byte[] bs2 = {6, 7, 8, 9, 10};
        byte[] bs = Bytes.concat(bs1, bs2);
        System.out.println(Bytes.asList(bs));//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    /**
     * 4、是否存在
     */
    @Test
    public void testContains() {
        byte b = 3;
        byte[] bs = {1, 2, 3, 4, 5};
        boolean result = Bytes.contains(bs, b);
        System.out.println(result);
    }

    @Test
    public void testIndexOf() {
        byte b = 3;
        byte[] bs = {1, 2, 3, 4, 5};
        int result = Bytes.indexOf(bs, b);
        System.out.println(result);//2
    }

    @Test
    public void testIndexOf2() {
        byte[] b1 = {2, 3};
        byte[] b2 = {1, 2, 3, 4, 5};
        int result = Bytes.indexOf(b2, b1);
        System.out.println(result);//1
    }
}
