package com.vnetsim2.core.util;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinBinaryHeap<Key> {
  private Key[] keys;
  private int size;
  private Comparator<Key> comparator;

  public MinBinaryHeap(int initCapacity) {
    keys = (Key[]) new Object[initCapacity + 1];
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

  public MinBinaryHeap(Key[] keys, Comparator<Key> comparator) {
    size = keys.length;
    this.comparator = comparator;
    this.keys = (Key[]) new Object[keys.length + 1];
    for (int i = 0; i < size; i++) {
      this.keys[i + 1] = keys[i];
    }
    int lastInnerNode = size / 2;
    for (int j = lastInnerNode; j > 0; j--) {
      sink(j);
    }
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

  public void insert(Key k) {
    if (size == keys.length - 1) {
      resize(2 * keys.length);
    }
    keys[++size] = k;
    swim(size);
  }

  public Key extractMin() {
    if (isEmpty()) {
      throw new NoSuchElementException("Heap underflow");
    }
    exchange(1, size);
    Key min = keys[size--];
    sink(1);
    keys[size + 1] = null;
    if ((size > 0) && (size == (keys.length - 1) / 4)) {
      resize(keys.length / 2);
    }
    return min;
  }

  public MinBinaryHeap<Key> clone() {
    MinBinaryHeap<Key> copy = new MinBinaryHeap<>(size, comparator);
    for (int i = 1; i <= size; i++) {
      copy.insert(keys[i]);
    }
    return copy;
  }

  private void resize(int capacity) {
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
    int parent = k / 2;
    while (k > 1 && greater(parent, k)) {
      exchange(k, parent);
      k = parent;
      parent = k / 2;
    }
  }

  private void sink(int k) {
    while (k * 2 <= size) {
      int left = k * 2;
      int right = left + 1;
      int smaller = left;
      if (right <= size && greater(left, right)) {
        smaller = right;
      }
      if (!greater(k, smaller)) {
        break;
      }
      exchange(k, smaller);
      k = smaller;
    }
  }
}
