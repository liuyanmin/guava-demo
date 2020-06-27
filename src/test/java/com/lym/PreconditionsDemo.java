package com.lym;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * Created by liuyanmin on 2020/6/23.
 */
public class PreconditionsDemo {

    /**
     * 1、表达式为false时抛出 java.lang.IllegalArgumentException
     */
    @Test
    public void testCheckArgument() {
        double d = 0.0d;
        // 第一个参数：表达式，第二个参数：异常信息，第三个参数：第二个参数的变量值
        Preconditions.checkArgument(d > 0, "Illegal Argument passed: Negative value %s.", d);
    }

    @Test
    public void testCheckNotNull() {
        Object obj = null;
        obj = Preconditions.checkNotNull(obj, "Illegal Argument passed: parameter is Null.");
    }

    @Test
    public void testCheckElementIndex() {
        int index = 5;
        int[] data = {1,2,3,4,5};
        Preconditions.checkElementIndex(index, data.length, "Illegal Argument passed: Invalid index.");
    }

    @Test
    public void testCheckState() {
        byte state = 4;
        Preconditions.checkState(state == 5, "Illegal Argument passed: parameter state exception.");
    }
}
