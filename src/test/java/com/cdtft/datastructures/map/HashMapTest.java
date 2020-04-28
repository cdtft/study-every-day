package com.cdtft.datastructures.map;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author : wangcheng
 * @date : 2020年04月28日 14:15
 */
public class HashMapTest {

    @Test
    public void putTest() {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0 ; i< 20; i ++) {
            map.put(i, String.valueOf(i));
        }
    }

}
