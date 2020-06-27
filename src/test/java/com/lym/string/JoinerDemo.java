package com.lym.string;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyanmin on 2020/6/20.
 */
public class JoinerDemo {

    private static final List<String> list1 = Arrays.asList("google","guava","java","scala","kafka");
    private static final List<String> list2 = Arrays.asList(null,"google","guava","java",null,"scala","kafka");

    @Test
    public void testJoiner() throws IOException {
        // 1、用指定的字符连接
        // 注意：若list1中要是有null，会报空指针异常
        String result = Joiner.on(",").join(list1);
        System.out.println(result);

        // 2、跳过连接中的null值
        result = Joiner.on(",").skipNulls().join(list2);
        System.out.println(result);

        // 3、有null值存在时用指定值代替
        result = Joiner.on(",").useForNull("DEFAULT").join(list2);
        System.out.println(result);

        // 4、把集合变成一个stringBuilder 或者stringBuffer
        StringBuffer stringBuffer = Joiner.on(",").useForNull("DEFAULT").appendTo(new StringBuffer(), list2);
        StringBuilder stringBuilder = Joiner.on(",").useForNull("DEFAULT").appendTo(new StringBuilder(), list2);
        System.out.println(stringBuffer);
        System.out.println(stringBuilder);

        // 5、对于map中key value的分隔
        Map<String, String> map = new HashMap<>();
        map.put("hello", "java");
        map.put("scala", "guava");
        result = Joiner.on("&").withKeyValueSeparator("=").join(map);
        System.out.println(result);
    }
}
