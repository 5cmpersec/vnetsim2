package com.vnetsim2.core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<ItemT> implements Iterable<ItemT> {
  private Node<ItemT> head;
  private int size;

  private static class Node<ItemT> {
    private ItemT item;
    private Node<ItemT> next;
  }

  public Bag() {
    head = null;
    size = 0;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    return size;
  }

  public void add(ItemT item) {
    Node<ItemT> oldHead = head;
    head = new Node<>();
    head.item = item;
    head.next = oldHead;
    size++;
  }

  public Iterator<ItemT> iterator() {
    return new ListIterator<ItemT>(head);
  }

  private class ListIterator<T> implements Iterator<ItemT> {
    private Node<ItemT> current;

    public ListIterator(Node<ItemT> head) {
      current = head;
    }

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public ItemT next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      ItemT item = current.item;
      current = current.next;
      return item;
    }
  }
}
