package com.vnetsim2.ui;

public class Point2D {
  private final double x;
  private final double y;
  private final String name;

  public Point2D(double x, double y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
  }

  public Point2D(double x, double y) {
    this(x, y, new String(""));
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public String name() {
    return name;
  }

  public double distance(Point2D point) {
    double dx = this.x - point.x;
    double dy = this.y - point.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

}
