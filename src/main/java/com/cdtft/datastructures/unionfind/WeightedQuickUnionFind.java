package com.cdtft.datastructures.unionfind;

import java.util.Arrays;

/**
 * 加权归并树
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月11日 16:51
 */
public class WeightedQuickUnionFind extends AbstractTreeUnionFind {

    private int[] id;

    private int[] sz;

    private int count;

    public WeightedQuickUnionFind(int n) {
        this.id = new int[n];
        this.sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
        this.count = n;
    }

    @Override
    public int[] id() {
        return id;
    }

    @Override
    public void union(int q, int p) {
        int qRootIdx = id[q];
        int pRootIdx = id[p];
        if (qRootIdx == pRootIdx) {
            return;
        }
        if (sz[q] < sz[p]) {
            id[q] = pRootIdx;
            sz[p] = sz[p] + 1;
        } else {
            id[p] = qRootIdx;
            sz[q] = sz[q] + 1;
        }
        count--;
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        WeightedQuickUnionFind unionFind = new WeightedQuickUnionFind(5);
        unionFind.union(0, 4);
        unionFind.union(4, 2);
        System.out.println(unionFind.connected(0, 2));
        System.out.println(unionFind.count());
        System.out.println(Arrays.toString(unionFind.id));
    }
}
