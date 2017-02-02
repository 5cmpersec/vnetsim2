package com.vnetsim2.core.util;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinBinaryHeap<KeyT> {
  private KeyT[] keys;
  private int size;
  private Comparator<KeyT> comparator;

  public MinBinaryHeap(int initCapacity) {
    keys = (KeyT[]) new Object[initCapacity + 1];
    size = 0;
  }

  public MinBinaryHeap() {
    this(1);
  }

  public MinBinaryHeap(int initCapacity, Comparator<KeyT> comparator) {
    this.comparator = comparator;
    keys = (KeyT[]) new Object[initCapacity + 1];
    size = 0;
  }

  public MinBinaryHeap(Comparator<KeyT> comparator) {
    this(1, comparator);
  }

  public MinBinaryHeap(KeyT[] keys, Comparator<KeyT> comparator) {
    size = keys.length;
    this.comparator = comparator;
    this.keys = (KeyT[]) new Object[keys.length + 1];
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

  public KeyT min() {
    if (isEmpty()) {
      throw new NoSuchElementException("Heap underflow");
    }
    return keys[1];
  }

  public void insert(KeyT key) {
    if (size == keys.length - 1) {
      resize(2 * keys.length);
    }
    keys[++size] = key;
    swim(size);
  }

  public KeyT extractMin() {
    if (isEmpty()) {
      throw new NoSuchElementException("Heap underflow");
    }
    exchange(1, size);
    KeyT min = keys[size--];
    sink(1);
    keys[size + 1] = null;
    if ((size > 0) && (size == (keys.length - 1) / 4)) {
      resize(keys.length / 2);
    }
    return min;
  }

  public MinBinaryHeap<KeyT> clone() {
    MinBinaryHeap<KeyT> copy = new MinBinaryHeap<>(size, comparator);
    for (int i = 1; i <= size; i++) {
      copy.insert(keys[i]);
    }
    return copy;
  }

  private void resize(int capacity) {
    KeyT[] temp = (KeyT[]) new Object[capacity];
    for (int i = 1; i <= size; i++) {
      temp[i] = keys[i];
    }
    keys = temp;
  }

  private boolean greater(int i, int j) {
    if (comparator == null) {
      return ((Comparable<KeyT>) keys[i]).compareTo(keys[j]) > 0;
    } else {
      return comparator.compare(keys[i], keys[j]) > 0;
    }
  }

  private void exchange(int i, int j) {
    KeyT temp = keys[i];
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
