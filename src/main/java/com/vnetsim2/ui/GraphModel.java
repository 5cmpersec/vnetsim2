package com.vnetsim2.ui;

import java.util.Iterator;
import java.util.Random;

import com.vnetsim2.core.Edge;
import com.vnetsim2.core.EdgeWeightedGraph;
import com.vnetsim2.core.KruskalMst;

public class GraphModel {
  EdgeWeightedGraph graph;
  KruskalMst mst;
  Point2D[] map;

  public GraphModel(int numberOfVertexes) {
    if (numberOfVertexes < 0) {
      throw new IllegalArgumentException("Number of vertices must be nonnegative");
    }
    graph = new EdgeWeightedGraph(numberOfVertexes);
    map = new Point2D[numberOfVertexes];
    // random generate position
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < numberOfVertexes; i++) {
      map[i] = new Point2D(random.nextInt(630) + 10, random.nextInt(630) + 10, String.valueOf(i));
    }
    // may not efficient
    for (int i = 0; i < numberOfVertexes; i++) {
      for (int j = i + 1; j < numberOfVertexes; j++) {
        Edge edge = new Edge(i, j, map[i].distance(map[j]));
        graph.addEdge(edge);
      }
    }
    mst = new KruskalMst(graph);
  }

  public Iterable<Line2D> getAllEdges() {
    return new Line2DIterator(graph.edges().iterator());
  }

  public Iterable<Line2D> getMstEdges() {
    return new Line2DIterator(mst.edges().iterator());
  }

  public Iterable<Point2D> getAllVertexes() {
    return new Point2DIterator();
  }

  private class Point2DIterator implements Iterator<Point2D>, Iterable<Point2D> {
    private int index = 0;

    @Override
    public Iterator<Point2D> iterator() {
      return this;
    }

    @Override
    public boolean hasNext() {
      return index < map.length;
    }

    @Override
    public Point2D next() {
      return map[index++];
    }

  }

  private class Line2DIterator implements Iterator<Line2D>, Iterable<Line2D> {
    Iterator<Edge> iterator;

    private Line2DIterator(Iterator<Edge> iterator) {
      this.iterator = iterator;
    }

    @Override
    public Iterator<Line2D> iterator() {
      return this;
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Line2D next() {
      Edge edge = iterator.next();
      int v = edge.either();
      int w = edge.other(v);
      return new Line2D(map[v], map[w]);
    }

  }
}
