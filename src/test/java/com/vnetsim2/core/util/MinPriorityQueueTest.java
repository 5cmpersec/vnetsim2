package com.vnetsim2.core.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinPriorityQueueTest {
    private Integer[] testData;

    @Before
    public void setUp() {
        testData = new Integer[] {5, 6, 3, 1, 18, 9, 0, 10, 99};
    }

    @After
    public void tearDown() {
    }

    @Test (expected = NoSuchElementException.class)
    public void testMinWhenEmpty() {
        MinPriorityQueue<Integer> mpq = new MinPriorityQueue<>();
        assertEquals(true, mpq.isEmpty());
        int min = mpq.min();
    }

    @Test
    public void testMinWhenInsertOneByOne() {
        MinPriorityQueue<Integer> mpq = new MinPriorityQueue<>();
        for (int i = 0; i < testData.length; i++) {
            mpq.insert(testData[i]);
        }
        assertEquals(testData.length, mpq.size());
        assertEquals(Collections.min(Arrays.asList(testData)), mpq.min());
    }

    @Test
    public void testMinWhenInsertBulk() {
        MinPriorityQueue<Integer> mpq = new MinPriorityQueue<>(testData, null);
        assertEquals(testData.length, mpq.size());
        assertEquals(Collections.min(Arrays.asList(testData)), mpq.min());
    }

    @Test (expected = NoSuchElementException.class)
    public void testExtractMinWhenEmpty() {
        MinPriorityQueue<Integer> mpq = new MinPriorityQueue<>();
        assertEquals(true, mpq.isEmpty());
        int min = mpq.extractMin();
    }

    @Test
    public void testExtractMin() {
        MinPriorityQueue<Integer> mpq = new MinPriorityQueue<>(testData, null);
        Integer[] expected = testData.clone();
        Arrays.sort(expected);

        for (int i = 0; i < testData.length; i++) {
            assertEquals(expected[i], mpq.extractMin());
        }
        assertEquals(0, mpq.size());
    }

    @Test
    public void testIterator() {
        MinPriorityQueue<Integer> mpq = new MinPriorityQueue<>(testData, null);
        Integer[] expected = testData.clone();
        Arrays.sort(expected);

        assertEquals(testData.length, mpq.size());
        int i = 0;
        for (Integer k : mpq) {
            assertEquals(expected[i], k);
            i++;
        }
        assertEquals(testData.length, mpq.size());
    }
}
