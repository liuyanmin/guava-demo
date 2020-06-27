package com.lym.basic;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class DoublesDemo {

    @Test
    public void testAsList() {
        double[] array = {116.33283d, 39.996496d, 116.376873d, 39.866559d};
        List<Double> list = Doubles.asList(array);
        System.out.println(list);
    }

    @Test
    public void testToArray() {
        List<Double> list = Lists.newArrayList(100.2d, 100.5d);
        double[] array = Doubles.toArray(list);
        System.out.println(Doubles.asList(array));//[100.2, 100.5]
    }

    @Test
    public void testCompare() {
        double d1 = 100.1d;
        double d2 = 100.2d;
        int result = Doubles.compare(d1, d2);
        System.out.println(result);//-1
    }

    @Test
    public void testConcat() {
        double[] array1 = {116.33283d, 39.996496d, 116.376873d, 39.866559d};
        double[] array2 = {116.461841};
        double[] result = Doubles.concat(array1, array2);
        System.out.println(Doubles.asList(result));//[116.33283, 39.996496, 116.376873, 39.866559, 116.461841]
    }

    @Test
    public void testContains() {
        double[] array = {116.33283d, 39.996496d, 116.376873d, 39.866559d};
        double target = 39.996496d;
        boolean result = Doubles.contains(array, target);
        System.out.println(result);//true
    }

    @Test
    public void testIndexOf() {
        double[] array = {100.1d, 100.5d, 100.3d, 100.4d};
        double target = 100.3d;
        int index = Doubles.indexOf(array, target);
        System.out.println(index);//2
    }

    @Test
    public void testIndexOf2() {
        double[] array = {100.1d, 100.5d, 100.3d, 100.4d};
        double[] target = {100.5d, 100.3d};
        int index = Doubles.indexOf(array, target);
        System.out.println(index);//1
    }

    @Test
    public void testIsFinite() {
        boolean result = Doubles.isFinite(200.2d);
        System.out.println(result);//true
    }

    @Test
    public void testJoin() {
        double[] array = {100.1d, 100.5d, 100.3d, 100.4d};
        String result = Doubles.join(",", array);
        System.out.println(result);//100.1,100.5,100.3,100.4
    }

    @Test
    public void testLastIndexOf() {
        double[] array = {100.1d, 100.5d, 100.1d, 100.3d};
        double target = 100.1d;
        int index = Doubles.lastIndexOf(array, target);
        System.out.println(index);//2
    }

    @Test
    public void testMax() {
        double[] array = {100.1d, 100.5d, 100.1d, 100.3d};
        double result = Doubles.max(array);
        System.out.println(result);//100.5
    }

    @Test
    public void testMin() {
        double[] array = {100.1d, 100.5d, 100.1d, 100.3d};
        double result = Doubles.min(array);
        System.out.println(result);//100.1
    }

    @Test
    public void testStringConverter() {
        Converter<String, Double> converter = Doubles.stringConverter();
        Double result = converter.convert("100.2");
        System.out.println(result);//100.2
    }

    @Test
    public void testTryParse() {
        Double result = Doubles.tryParse("100.6");
        System.out.println(result);//100.6
    }
}
