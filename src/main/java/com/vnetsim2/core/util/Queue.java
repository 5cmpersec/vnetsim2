package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

/**
 * Represents a FIFO queue of generic items.
 *
 * @param <ItemT> the generic type of an item.
 */
public class Queue<ItemT> {
  private Node<ItemT> head;
  private Node<ItemT> tail;
  private int size;

  private static class Node<ItemT> {
    private ItemT item;
    private Node<ItemT> next;
  }

  /**
   * Initialize an empty queue.
   */
  public Queue() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Returns true if this queue is empty.
   * O(1)
   *
   * @return {@code true} if this queue is empty,
   *         {@code false} otherwise.
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Returns the number of items in this queue.
   * O(1)
   *
   * @return the number of items in this queue.
   */
  public int size() {
    return size;
  }

  /**
   * Adds an item to this queue.
   * O(1)
   *
   * @param item the item to add.
   */
  public void enqueue(ItemT item) {
    Node<ItemT> temp = tail;
    tail = new Node<>();
    tail.item = item;
    tail.next = null;
    if (temp == null) {
      head = tail;
    } else {
      temp.next = tail;
    }
    size++;
  }

  /**
   * Removes and returns an item on this queue that was least recently added.
   *
   * @return an item on this queue that was least recently added.
   */
  public ItemT dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    }
    ItemT temp = head.item;
    head = head.next;
    size--;
    if (isEmpty()) {
      tail = null;
    }
    return temp;
  }
}
