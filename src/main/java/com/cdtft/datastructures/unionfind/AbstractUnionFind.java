package com.cdtft.datastructures.unionfind;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月03日 15:02
 */
public abstract class AbstractUnionFind implements UnionFind {

    @Override
    public boolean connected(int q, int p) {
        return find(q) == find(p);
    }
}
