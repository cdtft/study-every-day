package com.cdtft.leetcode;

/**
 * @author : wangcheng
 * @date : 2020年05月07日 15:24
 */
public class TreeNode {

    private int val;

    private TreeNode l;

    private TreeNode r;

    TreeNode(int x) { val = x; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getL() {
        return l;
    }

    public void setL(TreeNode l) {
        this.l = l;
    }

    public TreeNode getR() {
        return r;
    }

    public void setR(TreeNode r) {
        this.r = r;
    }
}
