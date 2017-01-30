package com.vnetsim2.core;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EdgeWeightedGraphTest {

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidInput() {
    EdgeWeightedGraph ewg = new EdgeWeightedGraph(-1);
  }

  @Test
  public void testConstructor() {
    InputStream in =
        this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/tinyEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);

    assertEquals(8, graph.V());
    assertEquals(16, graph.E());
  }

  @Test
  public void testClone() {
    InputStream in =
        this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/tinyEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);
    assertEquals(8, graph.V());
    assertEquals(16, graph.E());
    EdgeWeightedGraph copy = graph.clone();
    assertEquals(8, copy.V());
    assertEquals(16, copy.E());
    for (int i = 0; i < graph.V(); i++) {
      assertEquals(graph.degree(i), copy.degree(i));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdjThrowException() {
    InputStream in =
        this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/tinyEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);
    int v = graph.V();
    // throw exception
    graph.adj(v);
  }

  @Test
  public void testAdj() {
    InputStream in =
        this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/tinyEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);
    for (int i = 0; i < graph.V(); i++) {
      for (Edge e : graph.adj(i)) {
        int v = e.either();
        assertEquals(true, (v == i || e.other(v) == i));
      }
    }
  }

  @Test
  public void testEdges() {
    InputStream in =
        this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/mediumEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);
    int count = 0;
    for (Edge e : graph.edges()) {
      count++;
    }
    assertEquals(graph.E(), count);
  }

  private EdgeWeightedGraph createGraphFromInputStream(InputStream in) {
    Scanner scanner = new Scanner(new BufferedInputStream(in), StandardCharsets.UTF_8.name());
    scanner.useLocale(Locale.US);

    int V = scanner.nextInt();
    int E = scanner.nextInt();
    EdgeWeightedGraph graph = new EdgeWeightedGraph(V);
    for (int i = 0; i < E; i++) {
      int v = scanner.nextInt();
      int w = scanner.nextInt();
      double weight = scanner.nextDouble();
      Edge e = new Edge(v, w, weight);
      graph.addEdge(e);
    }
    return graph;
  }
}
