package com.cdtft.datastructures.unionfind;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月11日 17:17
 */
public abstract class AbstractTreeUnionFind extends AbstractUnionFind {

    @Override
    public int find(int index) {
        while(id()[index] != index) {
            index = id()[index];
        }
        return index;
    }

}
