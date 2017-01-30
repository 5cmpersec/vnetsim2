package com.vnetsim2.core.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MinBinaryHeapTest {
  private Integer[] testData;

  @Before
  public void setUp() {
    testData = new Integer[] {5, 6, 3, 1, 18, 9, 0, 10, 99};
  }

  @After
  public void tearDown() {}

  @Test(expected = NoSuchElementException.class)
  public void testMinWhenEmpty() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
    assertEquals(true, heap.isEmpty());
    int min = heap.min();
  }

  @Test
  public void testMinWhenInsertOneByOne() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
    for (int i = 0; i < testData.length; i++) {
      heap.insert(testData[i]);
    }
    assertEquals(testData.length, heap.size());
    assertEquals(Collections.min(Arrays.asList(testData)), heap.min());
  }

  @Test
  public void testMinWhenInsertBulk() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>(testData, null);
    assertEquals(testData.length, heap.size());
    assertEquals(Collections.min(Arrays.asList(testData)), heap.min());
  }

  @Test(expected = NoSuchElementException.class)
  public void testExtractMinWhenEmpty() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>();
    assertEquals(true, heap.isEmpty());
    int min = heap.extractMin();
  }

  @Test
  public void testExtractMin() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>(testData, null);
    Integer[] expected = testData.clone();
    Arrays.sort(expected);

    for (int i = 0; i < testData.length; i++) {
      assertEquals(expected[i], heap.extractMin());
    }
    assertEquals(0, heap.size());
  }

  @Test
  public void testClone() {
    MinBinaryHeap<Integer> heap = new MinBinaryHeap<>(testData, null);
    MinBinaryHeap<Integer> clone = heap.clone();

    for (int i = 0; i < testData.length; i++) {
      assertEquals(heap.extractMin(), clone.extractMin());
      assertEquals(heap.size(), clone.size());
    }
  }
}
