package com.vnetsim2.core;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeWeightedGraphTest {
    
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("EdgeWeightedGraph/tinyEWG.txt");
        EdgeWeightedGraph graph = createGraphFromInputStream(in);

        assertEquals(8, graph.V());
        assertEquals(16, graph.E());
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
