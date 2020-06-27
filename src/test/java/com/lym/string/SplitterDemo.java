package com.lym.string;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by liuyanmin on 2020/6/20.
 */
public class SplitterDemo {

    @Test
    public void testSplitter() {
        /********************** 按字符串进行分割 *********************/
        // 1、用指定字符切分字符串,并转换成list
        String s1 = "hello|world";
        String s2 = "hello | world | | |";
        List<String> list1 = Splitter.on("|").splitToList(s1);
        List<String> list2 = Splitter.on("|").splitToList(s2);
        System.out.println(list1);
        System.out.println(list2);

        // 2、忽略掉空的字符串或者多余的分割符
        list2 = Splitter.on("|").omitEmptyStrings().splitToList(s2);
        System.out.println(list2);

        // 3、忽略掉字符串中的空格
        list2 = Splitter.on("|").omitEmptyStrings().trimResults().splitToList(s2);
        System.out.println(list2);

        // 4、固定长度分割
        String s3 = "aaaabbbbccccdddd";
        List<String> list3 = Splitter.fixedLength(5).splitToList(s3);
        System.out.println(list3);

        // 5、指定长度分割
        String s4 = "a1#b2#c3#d4#e5";
        List<String> list4 = Splitter.on("#").limit(3).splitToList(s4);
        System.out.println(list4);

        // 6、string to map
        String s5 = "a=1,b=2";
        Map<String, String> map = Splitter.on(",").withKeyValueSeparator("=").split(s5);
        System.out.println(map);


        /********************** 按正则表达式进行分割 *********************/
        // 1、传入字符的分割
        String s6 = "hello,world.java";
        List<String> list5 = Splitter.onPattern("[.|,]").splitToList(s6);
        System.out.println(list5);

        // 2、传入pattern的分割
        List<String> list6 = Splitter.on(Pattern.compile("[.|,]")).splitToList(s6);
        System.out.println(list6);

    }
}
