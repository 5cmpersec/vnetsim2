package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

/**
 * Represents a LIFO stack of generic item.
 *
 * @param <ItemT> the generic type of an item.
 */
public class Stack<ItemT> {
  private Node<ItemT> head;
  private int size;

  private static class Node<ItemT> {
    private ItemT item;
    private Node<ItemT> next;
  }

  /**
   * Initialize and empty stack.
   */
  public Stack() {
    head = null;
    size = 0;
  }

  /**
   * Returns true if this stack is empty.
   * O(1)
   *
   * @return {@code true} if this stack is empty,
   *         {@code false} otherwise.
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Returns the number of items in this stack.
   * O(1)
   *
   * @return the number of items in this stack.
   */
  public int size() {
    return size;
  }

  /**
   * Adds an item to this stack.
   * O(1)
   *
   * @param item the item to add.
   */
  public void push(ItemT item) {
    Node<ItemT> temp = head;
    head = new Node<>();
    head.item = item;
    head.next = temp;
    size++;
  }

  /**
   * Removes and returns an item on this queue that was most recently added.
   *
   * @return an item on this queue that was most recently added.
   */
  public ItemT pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    ItemT item = head.item;
    head = head.next;
    size--;
    return item;
  }
}
