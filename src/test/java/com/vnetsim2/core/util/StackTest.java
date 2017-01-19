package com.vnetsim2.core.util;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDefaultConstructor() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(0, stack.size());
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testPush() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        assertEquals(1, stack.size());
        assertEquals(false, stack.isEmpty());
        stack.push(1);
        assertEquals(2, stack.size());
    }

    @Test (expected = NoSuchElementException.class)
    public void testPopWhenStackEmpty() {
        Stack<Integer> stack = new Stack<>();
        int i = stack.pop();
    }

    @Test
    public void testPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        assertEquals(3, stack.size());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertEquals(0, (int) stack.pop());
        assertEquals(0, stack.size());
    }
}
