package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

public class Stack<Item> {
    private Node<Item> head;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack() {
        head = null;
        size =0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void push(Item item) {
        Node<Item> temp = head;
        head = new Node<Item>();
        head.item = item;
        head.next = temp;
        size++;
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Item item = head.item;
        head = head.next;
        size--;
        return item;
    }
}
