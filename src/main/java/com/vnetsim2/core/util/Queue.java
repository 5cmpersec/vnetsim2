package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

public class Queue<ItemT> {
  private Node<ItemT> head;
  private Node<ItemT> tail;
  private int size;

  private static class Node<ItemT> {
    private ItemT item;
    private Node<ItemT> next;
  }

  public Queue() {
    head = null;
    tail = null;
    size = 0;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    return size;
  }

  public void enqueue(ItemT item) {
    Node<ItemT> oldTail = tail;
    tail = new Node<>();
    tail.item = item;
    tail.next = null;
    if (oldTail == null) {
      head = tail;
    } else {
      oldTail.next = tail;
    }
    size++;
  }

  public ItemT dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    }
    ItemT item = head.item;
    head = head.next;
    size--;
    if (isEmpty()) {
      tail = null;
    }
    return item;
  }
}
