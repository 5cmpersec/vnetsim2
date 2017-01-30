package com.vnetsim2.core.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BagTest {

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test
  public void testDefaultConstructor() {
    Bag<Integer> bag = new Bag<>();
    assertEquals(0, bag.size());
    assertEquals(true, bag.isEmpty());
  }

  @Test
  public void testAdd() {
    Bag<Integer> bag = new Bag<>();
    bag.add(0);
    assertEquals(1, bag.size());
    assertEquals(false, bag.isEmpty());
    bag.add(1);
    assertEquals(2, bag.size());
  }

  @Test
  public void testBagIteration() {
    Bag<Integer> bag = new Bag<>();
    int[] in = new int[100];
    Random rn = new Random();
    for (int i = 0; i < in.length; i++) {
      in[i] = rn.nextInt(100);
      bag.add(in[i]);
    }
    assertEquals(in.length, bag.size());

    int[] out = new int[in.length];
    int i = 0;
    for (int item : bag) {
      out[i] = item;
      i++;
    }

    Arrays.sort(in);
    Arrays.sort(out);
    assertEquals(true, Arrays.equals(in, out));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveData() {
    Bag<Integer> bag = new Bag<>();
    bag.add(100);
    assertEquals(1, bag.size());
    Iterator<Integer> iter = bag.iterator();
    while (iter.hasNext()) {
      iter.remove();
    }
  }

  @Test(expected = NoSuchElementException.class)
  public void testIteratorGetDataWhenEmpty() {
    Bag<Integer> bag = new Bag<>();
    assertEquals(true, bag.isEmpty());
    Iterator<Integer> iter = bag.iterator();
    Integer i = iter.next();
  }
}
