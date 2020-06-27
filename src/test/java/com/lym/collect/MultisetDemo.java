package com.lym.collect;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.List;

/**
 * 可以有重复的值
 * Created by liuyanmin on 2020/6/21.
 */
public class MultisetDemo {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("10", "20", "hello", "ha", "ha");
        Multiset<String> multiset = HashMultiset.create(list);

        System.out.println(multiset.contains("ha"));//true

        System.out.println(multiset.count("ha"));//2
        System.out.println(multiset.count("haha"));//0

        System.out.println(multiset.size());//5

        System.out.println(multiset.elementSet());//[ha, hello, 20, 10]

        System.out.println(multiset.entrySet());//[ha x 2, hello, 20, 10]
        multiset.entrySet().forEach(e -> System.out.print(e.getElement() + "-" + e.getCount() + " "));//ha-2 hello-1 20-1 10-1
        System.out.println();

        multiset.setCount("world", 3);
        System.out.println(multiset.entrySet());//[world x 3, ha x 2, hello, 20, 10]

        multiset.add("java", 2);
        System.out.println(multiset.entrySet());//[world x 3, java x 2, ha x 2, hello, 20, 10]

        multiset.remove("world", 2);
        System.out.println(multiset.entrySet());//[world, java x 2, ha x 2, hello, 20, 10]
    }
}
