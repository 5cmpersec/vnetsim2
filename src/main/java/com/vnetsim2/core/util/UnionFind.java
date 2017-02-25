package com.vnetsim2.core.util;

/**
 * Represents a union-find (disjoint-sets) data type.
 *
 */
public class UnionFind {
  private int[] parent;
  private byte[] rank;
  private int count;

  /**
   * Initializes an empty union–find data structure with n sites
   * 0 through n-1.
   * TimeComplexity: O(n) with n = number.
   *
   * @param  number the number of sites
   * @throws IllegalArgumentException if number < 0.
   */
  public UnionFind(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("Number of sites must be nonnegative");
    }
    count = number;
    parent = new int[number];
    rank = new byte[number];
    for (int i = 0; i < number; i++) {
      parent[i] = i;
      rank[i] = 0;
    }
  }

  /**
   * Returns the component identifier for the component containing site p.
   * TimeComplexity: nearly but not O(1)
   *
   * @param  pos the integer representing one site
   * @return the component identifier for the component containing site p
   * @throws IndexOutOfBoundsException unless 0 <= p < n.
   */
  public int find(int pos) {
    validate(pos);
    while (pos != parent[pos]) {
      parent[pos] = parent[parent[pos]];
      pos = parent[pos];
    }
    return pos;
  }

  /**
   * Returns the number of components.
   * TimeComplexity: O(1)
   *
   * @return the number of components (between 1 and n).
   */
  public int count() {
    return count;
  }

  /**
   * Returns true if the the two sites are in the same component.
   * TimeComplexity: nearly but not O(1)
   *
   * @param  pos1 the integer representing one site
   * @param  pos2 the integer representing the other site
   * @return {@code true} if the two sites p and q are in the same component;
   *         {@code false} otherwise
   * @throws IndexOutOfBoundsException unless
   *         both 0 <= pos1 < n and 0 <= pos2 < n
   */
  public boolean connected(int pos1, int pos2) {
    return find(pos1) == find(pos2);
  }

  /**
   * Merges the component containing site p with the
   * the component containing site q.
   * TimeComplexity: nearly but not O(1)
   *
   * @param  pos1 the integer representing one site
   * @param  pos2 the integer representing the other site
   * @throws IndexOutOfBoundsException unless
   *         both 0 <= pos1 < n and 0 <= pos2 < n
   */
  public void union(int pos1, int pos2) {
    int rootP = find(pos1);
    int rootQ = find(pos2);

    if (rootP == rootQ) {
      return;
    }

    if (rank[rootP] < rank[rootQ]) {
      parent[rootP] = rootQ;
    } else if (rank[rootP] > rank[rootQ]) {
      parent[rootQ] = rootP;
    } else {
      parent[rootQ] = rootP;
      rank[rootP]++;
    }
    count--;
  }

  private void validate(int p) {
    int len = parent.length;
    if (p < 0 || p >= len) {
      throw new IndexOutOfBoundsException("Index " + p + " is not between 0 and " + (len - 1));
    }
  }
}
