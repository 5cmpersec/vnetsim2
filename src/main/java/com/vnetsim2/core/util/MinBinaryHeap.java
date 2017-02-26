package com.vnetsim2.core.util;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a binary heap of generic keys.
 *
 * @param <KeyT> the generic type of key on this binary heap.
 */
public class MinBinaryHeap<KeyT> {
  private KeyT[] keys;
  private int size;
  private Comparator<KeyT> comparator;

  /**
   * Initializes an empty binary heap with the given capacity.
   *
   * @param initCapacity the initial capacity of this binary heap.
   */
  @SuppressWarnings("unchecked")
  public MinBinaryHeap(int initCapacity) {
    keys = (KeyT[]) new Object[initCapacity + 1];
    size = 0;
  }

  /**
   * Initializes an empty binary heap.
   */
  public MinBinaryHeap() {
    this(1);
  }

  /**
   * Initializes an empty binary heap with the given capacity,
   * using the given comparator.
   *
   * @param initCapacity the initial capacity of this binary heap.
   * @param comparator the comparator to use when comparing keys.
   */
  @SuppressWarnings("unchecked")
  public MinBinaryHeap(int initCapacity, Comparator<KeyT> comparator) {
    this.comparator = comparator;
    keys = (KeyT[]) new Object[initCapacity + 1];
    size = 0;
  }

  /**
   * Initializes an empty binary heap using the given comparator.
   *
   * @param comparator the comparator to use when comparing keys.
   */
  public MinBinaryHeap(Comparator<KeyT> comparator) {
    this(1, comparator);
  }

  /**
   * Initializes an empty binary heap from the array of keys,
   * using the given comparator.
   *
   * @param keys the array of keys.
   * @param comparator the comparator to use when comparing keys.
   */
  @SuppressWarnings("unchecked")
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


  /**
   * Returns true if this binary heap is empty.
   * TC: O(1)
   *
   * @return {@code true} if this binary heap is empty;
   *         {@code false} otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of keys on this binary heap.
   * TC: O(1)
   *
   * @return the number of keys on this binary heap.
   */
  public int size() {
    return size;
  }

  /**
   * Returns a smallest key on this binary heap.
   * TC: O(1)
   *
   * @return a smallest key on this binary heap.
   * @throws NoSuchElementException if this binary heap is empty
   */
  public KeyT min() {
    if (isEmpty()) {
      throw new NoSuchElementException("Heap underflow");
    }
    return keys[1];
  }

  /**
   * Adds a new key to this binary heap.
   * TC: O(lgn)
   *
   * @param key the key to add to this binary heap
   */
  public void insert(KeyT key) {
    if (size == keys.length - 1) {
      resize(2 * keys.length);
    }
    keys[++size] = key;
    swim(size);
  }

  /**
   * Removes and returns a smallest key on this binary heap.
   * TC: O(lgn)
   *
   * @return a smallest key on this binary heap.
   * @throws NoSuchElementException if this binary heap is empty.
   */
  public KeyT extractMin() {
    if (isEmpty()) {
      throw new NoSuchElementException("Heap underflow");
    }
    exchange(1, size);
    KeyT temp = keys[size--];
    sink(1);
    keys[size + 1] = null;
    if ((size > 0) && (size == (keys.length - 1) / 4)) {
      resize(keys.length / 2);
    }
    return temp;
  }

  @Override
  public MinBinaryHeap<KeyT> clone() {
    MinBinaryHeap<KeyT> copy = new MinBinaryHeap<>(size, comparator);
    for (int i = 1; i <= size; i++) {
      copy.insert(keys[i]);
    }
    return copy;
  }

  private void resize(int capacity) {
    @SuppressWarnings("unchecked")
    KeyT[] temp = (KeyT[]) new Object[capacity];
    for (int i = 1; i <= size; i++) {
      temp[i] = keys[i];
    }
    keys = temp;
  }

  @SuppressWarnings("unchecked")
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