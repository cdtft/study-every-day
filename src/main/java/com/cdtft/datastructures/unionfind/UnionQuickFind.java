package com.cdtft.datastructures.unionfind;

import java.util.Arrays;

/**
 * union-find 快速查找
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月02日 15:21
 */
public class UnionQuickFind extends AbstractUnionFind {

    private int count;

    //下标代表触点，值为连通分量的索引
    private final int[] id;

    public UnionQuickFind(int n) {
        this.count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public int[] id() {
        return id;
    }

    @Override
    public void union(int q, int p) {
        if (connected(q, p)) {
            return;
        }
        int pId = id[p];
        int qId = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }

    @Override
    public int find(int index) {
        return id[index];
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        UnionQuickFind unionFind = new UnionQuickFind(5);
        unionFind.union(0, 4);
        unionFind.union(4, 2);
        System.out.println(unionFind.connected(0, 2));
        System.out.println(unionFind.count());
        System.out.println(Arrays.toString(unionFind.id));
    }

}
