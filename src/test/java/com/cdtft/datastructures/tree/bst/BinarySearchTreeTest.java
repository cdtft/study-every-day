package com.cdtft.datastructures.tree.bst;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void makeEmpty() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void insert() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(1);
        bst.insert(4);
        bst.insert(2);
        bst.insert(7);
        bst.insert(10);
        bst.insert(100);
    }
}