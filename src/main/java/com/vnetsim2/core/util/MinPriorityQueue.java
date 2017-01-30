package com.vnetsim2.core.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPriorityQueue<Key> implements Iterable<Key> {
  private MinBinaryHeap<Key> minHeap;

  public MinPriorityQueue(int initCapacity, Comparator<Key> comparator) {
    minHeap = new MinBinaryHeap<>(initCapacity, comparator);
  }

  public MinPriorityQueue() {
    this(1, null);
  }

  public MinPriorityQueue(Key[] keys, Comparator<Key> comparator) {
    minHeap = new MinBinaryHeap<>(keys, comparator);
  }

  public boolean isEmpty() {
    return minHeap.isEmpty();
  }

  public int size() {
    return minHeap.size();
  }

  public Key min() {
    return minHeap.min();
  }

  public Key extractMin() {
    return minHeap.extractMin();
  }

  public void insert(Key k) {
    minHeap.insert(k);
  }

  public Iterator<Key> iterator() {
    return new HeapSortIterator();
  }

  private class HeapSortIterator implements Iterator<Key> {
    private MinBinaryHeap<Key> copy;

    public HeapSortIterator() {
      copy = minHeap.clone();
    }

    public boolean hasNext() {
      return !copy.isEmpty();
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Key next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return copy.extractMin();
    }
  }
}
