package com.vnetsim2.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphRenderer {
  public enum Mode {
    ORIGINAL,
    MST
  };
  Canvas canvas;
  GraphModel model;

  public GraphRenderer(Canvas canvas) {
    this.canvas = canvas;
  }

  public void generateGraph(int number) {
    this.model = new GraphModel(number);
  }

  public void render(Mode mode) {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.GREEN);
    gc.setLineWidth(0.5);
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    if (mode == Mode.ORIGINAL) {
      gc.setStroke(Color.LIGHTGRAY);
      for (Line2D line : model.getAllEdges()) {
        drawLine(gc, line);
      }
    } else if (mode == Mode.MST) {
      gc.setStroke(Color.BLUE);
      for (Line2D line : model.getMstEdges()) {
        drawLine(gc, line);
      }
    }

    for (Point2D vertex : model.getAllVertexes()) {
      drawVertex(gc, vertex);
    }

  }

  private void drawVertex(GraphicsContext gc, Point2D vertex) {
    gc.fillOval(vertex.getX() - 8, vertex.getY() - 8, 16, 16);
    gc.fillText(vertex.name(), vertex.getX() + 8, vertex.getY() + 8);
  }

  private void drawLine(GraphicsContext gc, Line2D line) {
    gc.strokeLine(line.x1, line.y1, line.x2, line.y2);
  }

}
