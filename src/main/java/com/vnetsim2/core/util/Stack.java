package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

public class Stack<ItemT> {
  private Node<ItemT> head;
  private int size;

  private static class Node<ItemT> {
    private ItemT item;
    private Node<ItemT> next;
  }

  public Stack() {
    head = null;
    size = 0;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    return size;
  }

  public void push(ItemT item) {
    Node<ItemT> temp = head;
    head = new Node<>();
    head.item = item;
    head.next = temp;
    size++;
  }

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
