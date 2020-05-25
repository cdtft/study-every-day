package com.cdtft.datastructures.tree.bst;

/**
 * 二叉搜索树
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    private BinaryNode<T> insert(T t, BinaryNode<T> node) {
        //终止的条件是需要插入的node为null
        if (node == null) {
            return new BinaryNode<>(t, null, null);
        }
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0) {
            node.left = insert(t, node.left);
        } else if (compareResult > 0) {
            node.right = insert(t, node.right);
        } else {
            //do nothing
        }
        return node;
    }

    private static class BinaryNode<T> {

        private BinaryNode<T> left;

        private BinaryNode<T> right;

        private T element;

        public BinaryNode(T t) {
            this(t, null, null);
        }

        public BinaryNode(T t, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = t;
            this.left = left;
            this.right = right;
        }
    }

}
