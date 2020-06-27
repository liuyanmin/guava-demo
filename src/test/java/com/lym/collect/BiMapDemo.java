package com.lym.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * 双向map，可以通过key定位value，也可以通过value定位key
 * 注意：value也不能插入重复值，否则报错
 * Created by liuyanmin on 2020/6/21.
 */
public class BiMapDemo {

    @Test
    public void test() {
        BiMap<Integer, Integer> biMap = HashBiMap.create();
        biMap.put(1, 2);
        biMap.put(2, 3);
        biMap.put(3, 4);
        biMap.put(4, 5);
        System.out.println(biMap);//{1=2, 2=3, 3=4, 4=5}
        System.out.println(biMap.inverse());//{2=1, 3=2, 4=3, 5=4}
        biMap.put(5, 6);
        System.out.println(biMap);//1=2, 2=3, 3=4, 4=5, 5=6}
        System.out.println(biMap.inverse());//{2=1, 3=2, 4=3, 5=4, 6=5}
    }
}
