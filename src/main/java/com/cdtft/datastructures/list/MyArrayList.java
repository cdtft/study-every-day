package com.cdtft.datastructures.list;

import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    /**
     * default capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 当前数组中的元素
     */
    private int size;

    /**
     * 数组
     */
    private Object[] objects;

    public MyArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return (T) objects[index];
    }

    private void set(int index, T t) {
        if (index < 0 || index > objects.length) {
            throw new IndexOutOfBoundsException("MyArrayList超出数组边界");
        }
        this.objects[index] = t;
    }

    public void add(T t) {
        this.set(size++, t);
    }

    public void add(int index, T t) {
        if (objects.length == size()) {
            //扩容
            ensureCapacity(size() * 2);
        }
        //考虑如果index不是插入到队列尾部的时候向后移动位置


    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        Object[] obj = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            obj[i] = objects[i];
        }
        objects = obj;
        obj = null; //help gc
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
