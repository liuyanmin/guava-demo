package com.lym.collect;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by liuyanmin on 2020/6/20.
 */
public class SetsDemo {

    /**
     * 1、合并两个Set
     *  或者说，求连个set的并集
     */
    @Test
    public void testUnion() {
        Set<String> sets1 = Sets.newHashSet("A", "B", "B", "C");
        Set<String> sets2 = Sets.newHashSet("D", "E", "B", "C");
        Sets.SetView<String> result = Sets.union(sets1, sets2);
        System.out.println(result);//[A, B, C, D, E]
    }

    /**
     * 2、两个Set的交集
     */
    @Test
    public void testIntersection() {
        Set<String> sets1 = Sets.newHashSet("A", "B", "B", "C");
        Set<String> sets2 = Sets.newHashSet("D", "E", "B", "C");
        Sets.SetView<String> result = Sets.intersection(sets1, sets2);
        System.out.println(result);//[B, C]
    }

    /**
     * 3、sets1 不在 sets2 中
     */
    @Test
    public void testDifference() {
        Set<String> sets1 = Sets.newHashSet("A", "B", "B", "C");
        Set<String> sets2 = Sets.newHashSet("D", "E", "B", "C");
        Sets.SetView<String> result = Sets.difference(sets1, sets2);
        System.out.println(result);//[A]
    }

    /**
     * 4、在sets1不在sets2 + 在sets2不在sets1
     *  或者说，去掉 sets1 和 sets2 的差值
     */
    @Test
    public void testSymmetricDifference() {
        Set<String> sets1 = Sets.newHashSet("A", "B", "B", "C");
        Set<String> sets2 = Sets.newHashSet("D", "E", "B", "C");
        Sets.SetView<String> result = Sets.symmetricDifference(sets1, sets2);
        System.out.println(result);//[A, D, E]
    }

    /**
     * 5、过滤
     */
    @Test
    public void testFilter() {
        Set<Integer> sets = Sets.newHashSet(1,2,3,4,5,6);
        Set<Integer> result = Sets.filter(sets, e -> e <= 5);
        System.out.println(result);//[1, 2, 3, 4, 5]
    }

    /**
     * 6、笛卡尔积
     */
    @Test
    public void testCartesianProduct() {
        Set<String> sets1 = Sets.newHashSet("A", "B");
        Set<String> sets2 = Sets.newHashSet("C", "D");
        Set<List<String>> result = Sets.cartesianProduct(sets1, sets2);
        System.out.println(result);//[[A, D], [A, C], [B, D], [B, C]]
    }

    /**
     * 7、排列组合
     */
    @Test
    public void testCombinations() {
        Set<String> sets = Sets.newHashSet("A", "B", "C", "D");
        Set<Set<String>> result = Sets.combinations(sets, 2);
        result.forEach(System.out::print);//[A, B][A, C][B, C][A, D][B, D][C, D]
    }

    /**
     * 8、所有子集
     *  注意：包含空集合
     */
    @Test
    public void testPowerSet() {
        Set<String> sets = Sets.newHashSet("A", "B", "C");
        Set<Set<String>> result = Sets.powerSet(sets);
        result.forEach(System.out::print);//[][A][B][A, B][C][A, C][B, C][A, B, C]
    }

}
