package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDefaultConstructor() {
        Queue<Integer> queue = new Queue<>();
        assertEquals(0, queue.size());
        assertEquals(true, queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        assertEquals(1, queue.size());
        assertEquals(false, queue.isEmpty());
        queue.enqueue(1);
        assertEquals(2, queue.size());
    }

    @Test (expected = NoSuchElementException.class)
    public void testDequeueWhenQueueEmpty() {
        Queue<Integer> queue = new Queue<>();
        int i = queue.dequeue();
    }

    @Test
    public void testDequeue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(0, (int) queue.dequeue());
        assertEquals(1, (int) queue.dequeue());
        assertEquals(2, (int) queue.dequeue());
    }
}
