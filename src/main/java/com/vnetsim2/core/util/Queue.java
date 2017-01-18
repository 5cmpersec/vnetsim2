package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

public class Queue<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
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

    public void enqueue(Item item) {
        Node<Item> oldTail = tail;
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

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = head.item;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return item;
    }
}
