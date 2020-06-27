package com.lym.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 * Created by liuyanmin on 2020/6/21.
 */
public class CacheDemo {

    @Test
    public void test() throws ExecutionException {
        // 创建一个缓存
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)// 写入10分钟后过期
                .maximumSize(100)// 最多放100个key
                .concurrencyLevel(10)// 这个地方有点像分段锁提高性能的ConcurrentHashMap,大小100,level 10 分10段
                .weakKeys()// key 使用弱引用
                .weakValues()// value 使用弱引用
                .build();

        // 设置值
        cache.put("1001", "东直门");
        cache.put("1002", "西直门");
        Map<String, String> map = Maps.newHashMap();
        map.put("1006", "北京南");
        map.put("1007", "北京西");
        cache.putAll(map);
        System.out.println(cache.asMap());//{1007=北京西, 1001=东直门, 1006=北京南, 1002=西直门}

        // 获取值，若cache中没有，从Callback中获取并写入cache中，若getValue返回null会报错
        String value = cache.get("1003", () -> getValue("1003"));
        System.out.println(value);//望京
        value = cache.get("1001", () -> getValue("1001"));
        System.out.println(value);
        System.out.println(cache.asMap());//{1007=北京西, 1001=东直门, 1003=望京, 1006=北京南, 1002=西直门}

        // 获取当前cache中存在的值
        value = cache.getIfPresent("1003");
        System.out.println(value);//望京

        // 清除cache中的缓存
//        cache.cleanUp();
//        System.out.println(cache.asMap());

        // 缓存中key/value对的数量
        System.out.println(cache.size());// 5

        CacheStats cacheStats = cache.stats();
        System.out.println("命中数量: " + cacheStats.hitCount());
        System.out.println("命中比率: " + cacheStats.hitRate());
    }

    private String getValue(String id) {
        Map<String, String> map = Maps.newHashMap();
        map.put("1001", "东直门");
        map.put("1002", "西直门");
        map.put("1003", "望京");
        map.put("1004", "阜通");
        map.put("1005", "北京站");
        map.put("1006", "北京南");
        map.put("1007", "北京西");
        return map.get(id) == null ? "" : map.get(id);
    }
}
