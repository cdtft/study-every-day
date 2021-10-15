package com.cdtft.springframework.util;

import org.junit.Test;

/**
 * @author: wangcheng
 * @date: 2021年10月14 17:37
 */
public class IStringUtilsTest {

    @Test
    public void lowerFirst() {
        String testValue = "Abc";
        System.out.println(IStringUtils.lowerFirst(testValue));
    }
}