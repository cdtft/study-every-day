package com.cdtft.datastructures.unionfind;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月03日 14:31
 */
public interface UnionFind {

    int[] id();

    /**
     * 连接连个触点
     *
     * @param q
     * @param p
     */
    void union(int q, int p);

    /**
     * 查找该触点属于那个分量
     *
     * @param index
     * @return
     */
    int find(int index);

    /**
     * 连接两个触点
     *
     * @param q
     * @param p
     * @return
     */
    boolean connected(int q, int p);

    /**
     * 查询有多少连接分量
     *
     * @return
     */
    int count();

}
