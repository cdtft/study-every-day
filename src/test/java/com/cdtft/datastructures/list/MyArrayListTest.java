package com.cdtft.datastructures.list;

import org.junit.Test;

public class MyArrayListTest {

    @Test
    public void get() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        System.out.println(myArrayList.get(0));
    }

    @Test
    public void add() {
    }
}