package com.lym.collect;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.Test;

/**
 * 同一个key可以对应多个value
 * Created by liuyanmin on 2020/6/21.
 */
public class MultimapDemo {

    /**
     * 1、把key相同的value去重存到集合中
     */
    @Test
    public void testAsMap() {
        Multimap<Integer, Integer> multimap = HashMultimap.create();
        multimap.put(1, 2);
        multimap.put(1, 3);
        multimap.put(1, 2);
        multimap.put(2, 3);
        multimap.put(3, 2);
        multimap.put(3, 4);
        multimap.put(3, 2);
        multimap.put(3, 4);
        System.out.println(multimap.asMap());//{1=[2, 3], 2=[3], 3=[4, 2]}
        System.out.println(multimap.size());//5
    }

    @Test
    public void testReplaceValues() {
        Multimap<Integer, Integer> multimap = HashMultimap.create();
        multimap.put(1, 2);
        multimap.put(1, 3);
        multimap.put(1, 2);
        multimap.put(2, 3);
        multimap.put(3, 2);
        multimap.put(3, 4);
        multimap.put(3, 2);
        multimap.put(3, 4);
        multimap.replaceValues(1, Lists.newArrayList(4, 4, 5));
        System.out.println(multimap.asMap());//{1=[4, 5], 2=[3], 3=[4, 2]}
    }

    @Test
    public void test() {
        Multimap<Integer, Integer> multimap = HashMultimap.create();
        multimap.put(1, 2);
        multimap.put(1, 3);
        multimap.put(1, 2);
        multimap.put(2, 3);
        multimap.put(3, 2);
        multimap.put(3, 4);
        multimap.put(3, 2);
        multimap.put(3, 4);
        multimap.removeAll(1);// 把key=1的全删除
        multimap.remove(3, 2);// 把key=3，value=2的删除
        System.out.println(multimap.asMap());//{2=[3], 3=[4]}
    }
}
