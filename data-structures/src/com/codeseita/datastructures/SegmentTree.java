/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeseita.datastructures;

/**
 *
 * @author navin
 */
public abstract class SegmentTree {

    private int[] arr;
    private int[] tree;
    private int defaultValue;

    public SegmentTree(int[] arr, int defaultValue) {
        this.arr = arr;
        this.tree = new int[arr.length * 4];
        this.defaultValue = defaultValue;
    }

    public void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(node * 2, start, mid);
            build((node * 2) + 1, mid + 1, end);
            tree[node] = compare(tree[2 * node], tree[(2 * node) + 1]);;
        }
    }

    public void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            this.arr[start] = value;
            this.tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (start <= index && index <= mid) {
                update(node * 2, start, mid, index, value);
            } else {
                update((node * 2) + 1, mid + 1, end, index, value);
            }
            tree[node] = compare(tree[2 * node], tree[(2 * node) + 1]);
        }
    }

    public int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) { //range outside start and end
            return this.defaultValue;
        }
        if (l <= start && end <= r) { //start and end inside the range
            return this.tree[node];
        }
        int mid = (start + end) / 2;
        int a = query(node * 2, start, mid, l, r);
        int b = query((node * 2) + 1, mid + 1, end, l, r);
        return compare(a, b);
    }

    public int[] getTree() {
        return this.tree;
    }

    public abstract int compare(int a, int b);
}
