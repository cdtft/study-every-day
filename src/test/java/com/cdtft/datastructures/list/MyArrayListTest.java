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
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            myArrayList.add(i);
        }
        for (int j = 0; j < myArrayList.size(); j++) {
            System.out.println(myArrayList.get(j));
        }
    }

    @Test
    public void addIndex() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            myArrayList.add(0, i);
        }
        for (int j = 0; j < myArrayList.size(); j++) {
            System.out.println(myArrayList.get(j));
        }
    }
}