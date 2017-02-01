package com.vnetsim2.core;

import com.vnetsim2.core.util.Bag;
import com.vnetsim2.core.util.MinPriorityQueue;
import com.vnetsim2.core.util.UnionFind;

public class KruskalMst {
  private double weight;
  private Bag<Edge> mst;

  public KruskalMst(EdgeWeightedGraph G) {
    mst = new Bag<>();
    MinPriorityQueue<Edge> pq = new MinPriorityQueue<>();
    for (Edge e : G.edges()) {
      pq.insert(e);
    }

    UnionFind uf = new UnionFind(G.V());
    while (!pq.isEmpty() && mst.size() < G.V() - 1) {
      Edge edge = pq.extractMin();
      int v = edge.either();
      int w = edge.other(v);
      if (!uf.connected(v, w)) {
        uf.union(v, w);
        mst.add(edge);
        weight += edge.weight();
      }
    }
  }

  public Iterable<Edge> edges() {
    return mst;
  }

  public double weight() {
    return weight;
  }
}
