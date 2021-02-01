package com.cdtft.datastructures.search;

/**
 * 基于二叉查找树的符号表
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2021年01月25日 11:12
 */
public class BTS<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {

        private Key key;

        private Value value;

        private Node left, right;

        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 二叉搜索树的put方法
     *
     * @param node  处理本次put操作的节点
     * @param key
     * @param value
     * @return
     */
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int compare = node.key.compareTo(key);
        //相等本届点做更新操作
        if (compare == 0) {
            node.value = value;
        }
        //小于和大于的情况交给子节点处理
        if (compare > 0) {
            node.left = put(node.left, key, value);
        }
        if (compare < 0) {
            node.right = put(node.right, key, value);
        }
        //计算size
        node.N = size(node.left) + size(node.right) + 1;

        return node;
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = node.key.compareTo(key);
        if (compare == 0) {
            return node.value;
        }
        if (compare > 0) {
            return get(node.left, key);
        }
        return get(node.right, key);
    }

    public static void main(String[] args) {
        BTS<Integer, Integer> bts = new BTS<>();
        bts.put(1, 1);
        bts.put(2, 3);
        System.out.println("size: " + bts.size());
        System.out.println("key 1, value:" + bts.get(1));
    }
}
