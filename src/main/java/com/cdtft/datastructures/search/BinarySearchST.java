package com.cdtft.datastructures.search;

import java.util.Arrays;

/**
 * 二分查找
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2021年01月21日 11:40
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;

    private Value[] values;

    private int N;

    public BinarySearchST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Comparable[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N ;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (keys[mid].compareTo(key) == 0) {
                return mid;
            }
            if (keys[mid].compareTo(key) < 0) {
                lo = mid + 1;
            }
            if (keys[mid].compareTo(key) > 0) {
                hi = mid;
            }
        }
        return lo;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int index = rank(key);
        if (index < N && keys[index].compareTo(key) == 0) {
            return values[index];
        }
        return null;
    }

    public void put(Key key, Value value) {
        int index = rank(key);
        //更新重复key
        if (index < N && keys[index].compareTo(key) == 0) {
            values[index] = value;
            return;
        }
        //将key和value向后移动
        for (int i = N; i > index; i--) {
            values[i] = values[i - 1];
            keys[i] = keys[i - 1];
        }
        values[index] = value;
        keys[index] = key;
        N++;
    }

    public void showKeyAndValue() {
        System.out.println(Arrays.toString(keys));
        System.out.println(Arrays.toString(values));
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<>(5);
        binarySearchST.put(1, 2);
        binarySearchST.put(5, 5);
        binarySearchST.put(2, 2);
        binarySearchST.put(100, 100);
        binarySearchST.put(42, 42);
        binarySearchST.put(2, 2);
        binarySearchST.put(2, 2);
        binarySearchST.put(2, 2);
        binarySearchST.showKeyAndValue();
    }

}
