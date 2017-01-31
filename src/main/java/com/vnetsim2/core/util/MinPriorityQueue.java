package com.vnetsim2.core.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPriorityQueue<KeyT> implements Iterable<KeyT> {
  private MinBinaryHeap<KeyT> minHeap;

  public MinPriorityQueue(int initCapacity, Comparator<KeyT> comparator) {
    minHeap = new MinBinaryHeap<>(initCapacity, comparator);
  }

  public MinPriorityQueue() {
    this(1, null);
  }

  public MinPriorityQueue(KeyT[] keys, Comparator<KeyT> comparator) {
    minHeap = new MinBinaryHeap<>(keys, comparator);
  }

  public boolean isEmpty() {
    return minHeap.isEmpty();
  }

  public int size() {
    return minHeap.size();
  }

  public KeyT min() {
    return minHeap.min();
  }

  public KeyT extractMin() {
    return minHeap.extractMin();
  }

  public void insert(KeyT k) {
    minHeap.insert(k);
  }

  public Iterator<KeyT> iterator() {
    return new HeapSortIterator();
  }

  private class HeapSortIterator implements Iterator<KeyT> {
    private MinBinaryHeap<KeyT> copy;

    public HeapSortIterator() {
      copy = minHeap.clone();
    }

    public boolean hasNext() {
      return !copy.isEmpty();
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public KeyT next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return copy.extractMin();
    }
  }
}
