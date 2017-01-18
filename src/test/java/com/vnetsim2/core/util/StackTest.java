package com.vnetsim2.core.util;

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
        Stack<Integer> stack = new Stack<Integer>();
        assertEquals(0, stack.size());
    }

}
