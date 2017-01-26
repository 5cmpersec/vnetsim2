package com.vnetsim2.core;

import com.vnetsim2.core.util.Bag;
import com.vnetsim2.core.util.Stack;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph clone() {
        EdgeWeightedGraph copy = new EdgeWeightedGraph(this.V);
        copy.E = this.E;
        for (int v = 0; v < copy.V(); v++) {
            Stack<Edge> reverse = new Stack<>();
            for (Edge e : copy.adj[v]) {
                reverse.push(e);
            }
            while (!reverse.isEmpty()) {
                adj[v].add(reverse.pop());
            }
        }
        return copy;
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
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                        selfLoops++;
                    }
                }
            }
        }
        return list;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertext " + v + " must be value between 0 and " + (V - 1));
        }
    }
}
