package com.cdtft.datastructures.unionfind;

import java.util.Arrays;

/**
 * union find 快速连接
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月03日 11:25
 */
public class QuickUnionFind extends AbstractUnionFind {

    private int count;

    //使用数组表示森林，数组下标和数组中的值相等的时候为根节点
    private final int[] id;

    public QuickUnionFind(int n) {
        this.count = n;
        this.id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int q, int p) {
        int qRoot = find(q);
        int pRoot = find(p);
        if (qRoot == pRoot) {
            return;
        }
        id[qRoot] = pRoot;
        count--;
    }

    @Override
    public int find(int index) {
        while(id[index] != index) {
            index = id[index];
        }
        return index;
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        QuickUnionFind unionFind = new QuickUnionFind(5);
        unionFind.union(0, 4);
        unionFind.union(4, 2);
        System.out.println(unionFind.connected(0, 2));
        System.out.println(unionFind.count());
        System.out.println(Arrays.toString(unionFind.id));
    }

}
