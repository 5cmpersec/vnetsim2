package com.vnetsim2.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixEdgeWeightedGraph {

  private final int V;
  private int E;
  private Edge[][] adj;

  public AdjMatrixEdgeWeightedGraph(int V) {
    if (V < 0) {
      throw new IllegalArgumentException("number of vertices must be nonnegative");
    }
    this.V = V;
    this.E = 0;
    this.adj = new Edge[V][V];
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    validateVertex(v);
    validateVertex(w);
    if (adj[v][w] == null) {
      E++;
    }
    adj[v][w] = e;
    adj[w][v] = e;
  }

  private void validateVertex(int v) {
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
  }

  public Iterable<Edge> adj(int v) {
    validateVertex(v);
    return new AdjIterator(v);
  }

  private class AdjIterator implements Iterator<Edge>, Iterable<Edge> {
    private int v;
    private int w = 0;

    public AdjIterator(int v) {
      this.v = v;
    }

    @Override
    public Iterator<Edge> iterator() {
      return this;
    }

    @Override
    public boolean hasNext() {
      while (w < V) {
        if (adj[v][w] != null) {
          return true;
        }
        w++;
      }
      return false;
    }

    @Override
    public Edge next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return adj[v][w++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }
}