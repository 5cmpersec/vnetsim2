package com.vnetsim2.core.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnionFindTest {

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidInput() {
    UnionFind uf = new UnionFind(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testFindThrowException() {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("UnionFind/tinyUF.txt");
    UnionFind uf = createFromInputStream(in);
    // throw exception
    uf.find(100);
  }

  @Test
  public void testUFTinyData() {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("UnionFind/tinyUF.txt");
    UnionFind uf = createFromInputStream(in);
    assertEquals(2, uf.count());
  }

  @Test
  public void testUFmediumData() {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("UnionFind/mediumUF.txt");
    UnionFind uf = createFromInputStream(in);
    assertEquals(3, uf.count());
  }

  private UnionFind createFromInputStream(InputStream in) {
    Scanner scanner = new Scanner(new BufferedInputStream(in), StandardCharsets.UTF_8.name());
    scanner.useLocale(Locale.US);

    int N = scanner.nextInt();
    UnionFind uf = new UnionFind(N);
    while (scanner.hasNext()) {
      int p = scanner.nextInt();
      int q = scanner.nextInt();
      if (uf.connected(p, q)) {
        continue;
      }
      uf.union(p, q);
    }
    return uf;
  }
}
