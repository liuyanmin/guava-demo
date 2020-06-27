package com.lym.string;

import com.google.common.base.CaseFormat;
import org.junit.Test;

/**
 * Created by liuyanmin on 2020/6/20.
 */
public class CaseFormatDemo {

    @Test
    public void test() {
        // 1、is-check 转 isCheck
        String result = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "is-check");
        System.out.println(result);

        // 2、is_check 转 isCheck
        result = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "is_check");
        System.out.println(result);

        // 3、is-check 转 IsCheck
        result = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "is-check");
        System.out.println(result);

        // 4、is-check 转 IS_CHECK
        result = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, "is-check");
        System.out.println(result);
    }
}
