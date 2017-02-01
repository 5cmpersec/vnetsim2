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

public class KruskalMstTest {

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test
  public void testWeight_tinyEwg() {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/tinyEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);
    KruskalMst mst = new KruskalMst(graph);
    assertEquals(1.81000, mst.weight(), 0.000001);
  }

  @Test
  public void testWeight_mediumEwg() {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/mediumEWG.txt");
    EdgeWeightedGraph graph = createGraphFromInputStream(in);
    KruskalMst mst = new KruskalMst(graph);
    assertEquals(10.46351, mst.weight(), 0.000001);
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
