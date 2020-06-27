package com.lym.collect;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * Created by liuyanmin on 2020/6/20.
 */
public class ListsDemo {

    /**
     * 1、集合反转
     */
    @Test
    public void testReverse() {
        List<String> list = Lists.newArrayList("A", "B", "C");
        List<String> reverse = Lists.reverse(list);
        System.out.println(reverse);//[C, B, A]
    }

    /**
     * 2、去掉重复元素
     * 或者说，Set 转 List
     */
    @Test
    public void testRemoveRepet() {
        List<String> list = Lists.newArrayList(Sets.newHashSet("A", "A", "B", "C", "B"));
        System.out.println(list);//[A, B, C]
    }

    /**
     * 3、去掉集合中的null（Java8的方式）
     */
    @Test
    public void testRemoveNull() {
        List<String> list = Lists.newArrayList(Sets.newHashSet("A", null, "B", "C"));
        list.removeIf(Objects::isNull);
        System.out.println(list);//[A, B, C]
    }

    /**
     * 4、集合分区
     */
    @Test
    public void testPartition() {
        List<String> list = Lists.newArrayList("A", "B", "C", "D", "E");
        List<List<String>> listList = Lists.partition(list, 2);// 第二个参数指每个集合中元素的个数
        System.out.println(listList);//[[A, B], [C, D], [E]]
    }

    /**
     * 5、笛卡尔乘积
     */
    @Test
    public void testCartesianProduct() {
        List<String> list1 = Lists.newArrayList("A", "B");
        List<String> list2 = Lists.newArrayList("C", "D");
        List<List<String>> result = Lists.cartesianProduct(list1, list2);
        System.out.println(result);//[[A, C], [A, D], [B, C], [B, D]]
    }

    /**
     * 6、集合转换（用处不大，可以直接使用Java8）
     */
    @Test
    public void testTransform() {
        List<People> peopleList = Lists.newArrayList(new People("张珊"), new People("李思"));
        List<String> names = Lists.transform(peopleList, People::getName);
        System.out.println(names);//[张珊, 李思]
    }

    /**
     * 7、字符串转Character集合
     */
    @Test
    public void testCharactersOf() {
        List<Character> list = Lists.charactersOf("abcdefg");
        System.out.println(list);
    }

    class People {
        private String name;

        public People(String name) {
            this.name = name;
        }

        public People() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
