package com.vnetsim2.ui;

public class Line2D {
  double x1;
  double y1;
  double x2;
  double y2;

  public Line2D(double x1, double y1, double x2, double y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public Line2D(Point2D from, Point2D to) {
    this(from.getX(), from.getY(), to.getX(), to.getY());
  }

}
