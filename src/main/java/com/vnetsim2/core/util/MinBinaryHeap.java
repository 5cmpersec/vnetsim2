package com.vnetsim2.core.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinBinaryHeap<Key> implements Iterable<Key> {
    private Key[] keys;
    private int size;
    private Comparator<Key> comparator;

    public MinBinaryHeap(int initCapacity) {
        keys = (Key[]) new Object[initCapacity +  1];
        size = 0;
    }

    public MinBinaryHeap() {
        this(1);
    }

    public MinBinaryHeap(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        keys = (Key[]) new Object[initCapacity + 1];
        size = 0;
    }

    public MinBinaryHeap(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap underflow");
        }
        return keys[1];
    }

    private void resize(int capacity) {
        assert capacity > size;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = keys[i];
        }
        keys = temp;
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) keys[i]).compareTo(keys[j]) > 0;
        } else {
            return comparator.compare(keys[i], keys[j]) > 0;
        }
    }

    private void exchange(int i, int j) {
        Key temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }

    private void swim(int k) {
        int parent = k/2;
        while (k > 1 && greater(parent, k)) {
            exchange(k, parent);
            k = parent;
        }
    }

    private void sink(int k) {
        while (k*2 <= size) {
            int left = k*2;
            int right = left + 1;
            int smaller = left;
            if (right <= size && greater(left, right) {
                smaller = right;
            }
            if (!greater(k, smaller) {
                break;
            }
            exchange(k, smaller);
            k = smaller;
        }
    }
}
