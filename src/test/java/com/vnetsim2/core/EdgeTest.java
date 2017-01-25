package com.vnetsim2.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDefaultConstructor() {
        int tV = 1;
        int tW = 2;
        double tWeight = 3.141592;
        Edge e = new Edge(tV, tW, tWeight);
        assertEquals(tWeight, e.weight(), 0.000001);
        int v = e.either();
        assertEquals(true, ((v == tV) || (v == tW)));
        int w = e.other(v);
        assertEquals(true, ((w == tV) || (w == tW)));
    }

    @Test
    public void testEdgeCompare() {
        Edge e = new Edge(3, 4, 3.121212);
        Edge equal = new Edge(5, 6, 3.121212);
        Edge less = new Edge(7, 8, 3.01010101);
        Edge greater = new Edge(9, 10, 9.123456);

        assertEquals(true, e.compareTo(equal) == 0);
        assertEquals(true, e.compareTo(less) > 0);
        assertEquals(true, e.compareTo(greater) < 0);
    }
}
