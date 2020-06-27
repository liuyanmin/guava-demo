package com.lym.math;

import com.google.common.math.IntMath;
import org.junit.Test;

import java.math.RoundingMode;

/**
 * Created by liuyanmin on 2020/6/23.
 */
public class IntMathDemo {

    /**
     * 1、加
     */
    @Test
    public void testCheckedAdd() {
        int result = IntMath.checkedAdd(5, 4);
        System.out.println(result);//9
    }

    /**
     * 2、减
     */
    @Test
    public void testCheckedSubtract() {
        int result = IntMath.checkedSubtract(6, 2);
        System.out.println(result);//4
    }

    /**
     * 3、乘
     */
    @Test
    public void testCheckedMultiply() {
        int result = IntMath.checkedMultiply(4, 5);
        System.out.println(result);
    }

    /**
     * 4、除
     */
    @Test
    public void testCheckedDivide() {
        int result = IntMath.divide(17, 4, RoundingMode.HALF_UP);
        System.out.println(result);//4
    }

    /**
     * 5、b的k次幂
     */
    @Test
    public void testCheckedPow() {
        int result = IntMath.checkedPow(2, 3);
        System.out.println(result);//8
        result = IntMath.pow(2, 3);
        System.out.println(result);//8
    }

    /**
     * 6、n的阶乘
     */
    @Test
    public void testFactorial() {
        int result = IntMath.factorial(5);
        System.out.println(result);//120
    }

    /**
     * 7、a,b的最大公约数
     */
    @Test
    public void testGcd() {
        int result = IntMath.gcd(12, 18);
        System.out.println(result);//6
    }

    /**
     * 8、x的二次幂
     */
    @Test
    public void testIsPowerOfTwo() {
        boolean result = IntMath.isPowerOfTwo(5);
        System.out.println(result);//false
        result = IntMath.isPowerOfTwo(16);
        System.out.println(result);//true
    }

    /**
     * 9、x和y的算术平均值
     */
    @Test
    public void testMean() {
        int result = IntMath.mean(10, 3);
        System.out.println(result);//6
    }

    /**
     * 10、x模m
     */
    @Test
    public void testMod() {
        int result = IntMath.mod(10, 4);
        System.out.println(result);//2
    }

    /**
     * 11、x的平方根
     */
    @Test
    public void testSqrt() {
        int result = IntMath.sqrt(18, RoundingMode.UP);
        System.out.println(result);//5
    }
}
