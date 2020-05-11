package com.cdtft.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年05月11日 11:13
 */
public class MinStack {

    private Integer minValue;

    private List<Node> nodeList;

    public MinStack() {
        this.nodeList = new ArrayList<>();
    }

    public void push(int x) {
        if (nodeList.size() == 0) {
            minValue = x;
        }

        nodeList.add(new Node(x - minValue, x));
        if (x  < minValue) {
            minValue = x;
        }
    }

    public void pop() {
        if (nodeList.size() == 0) {
            return;
        }
        Node node = nodeList.remove(nodeList.size() - 1);
        if (node.diff < 0) {
            refreshMinValue(node);
        }
    }

    public int top() {
        if (nodeList.size() == 0) {
            return Integer.MAX_VALUE;
        }
        Node node = nodeList.get(nodeList.size() - 1);
        return node.value;
    }

    public int getMin() {
        return minValue;
    }

    private void refreshMinValue(Node node) {
        minValue = node.value - node.diff;
    }

    private class Node {

        Integer diff;

        Integer value;

        public Node(Integer diff, Integer value) {
            this.diff = diff;
            this.value = value;
        }
    }
}
