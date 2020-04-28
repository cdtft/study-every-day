package com.cdtft.sdk.string;

import org.junit.Test;

/**
 * @author : wangcheng
 * @date : 2020年04月17日 14:16
 */
public class StringTest {

    @Test
    public void TestConstructValueOffsetCount() {
        char[] chars = new char[]{'a', 'b', 'c','d'};
        String cd = new String(chars, 2, 0);
        System.out.println(cd);
    }

    @Test
    public void TestCodePointMethod() {
        String test = "abcd";
        System.out.println(test.codePointAt(0));
        char a = 'a';
        System.out.println(a + 0);
    }

    @Test
    public void TestCompareTo() {
        String test = "aaa";
        System.out.println(test.compareTo("abcd"));
    }
}
