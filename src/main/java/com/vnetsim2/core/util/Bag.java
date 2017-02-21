package com.vnetsim2.core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a bag of generic items.
 *
 * @param <ItemT> the generic type of an item.
 */
public class Bag<ItemT> implements Iterable<ItemT> {
  private Node<ItemT> head;
  private int size;

  private static class Node<ItemT> {
    private ItemT item;
    private Node<ItemT> next;
  }

  /**
   * Initialize an empty bag.
   */
  public Bag() {
    head = null;
    size = 0;
  }

  /**
   * Returns true if this bag is empty.
   * O(1)
   *
   * @return {@code true} if this bag is empty,
   *         {@code false} otherwise.
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Returns the number of items in this bag.
   * O(1)
   *
   * @return the number of items in this bag.
   */
  public int size() {
    return size;
  }

  /**
   * Adds a item to this bag.
   * O(1)
   *
   * @param item the item to add.
   */
  public void add(ItemT item) {
    Node<ItemT> oldHead = head;
    head = new Node<>();
    head.item = item;
    head.next = oldHead;
    size++;
  }

  /**
   * Returns an iterator that iterates over the items in this bag in arbitrary order.
   *
   * @return an iterator.
   */
  @Override
  public Iterator<ItemT> iterator() {
    return new ListIterator<ItemT>(head);
  }

  private class ListIterator<T> implements Iterator<ItemT> {
    private Node<ItemT> current;

    public ListIterator(Node<ItemT> head) {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
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
