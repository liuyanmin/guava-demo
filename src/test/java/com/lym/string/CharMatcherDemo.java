package com.lym.string;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * Created by liuyanmin on 2020/6/20.
 */
public class CharMatcherDemo {

    @Test
    public void test() {
        String result = CharMatcher.whitespace().removeFrom(" a b   c");
        System.out.println(result);

        int a = CharMatcher.any().indexIn("a");
        System.out.println(a);
    }
}
