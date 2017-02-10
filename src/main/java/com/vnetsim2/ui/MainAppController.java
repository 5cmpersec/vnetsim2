package com.vnetsim2.ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MainAppController {

  final int ALERT_INVALID_INPUT_NUMBER = 0;

  @FXML
  private Button btnGenerate;

  @FXML
  private TextField txtGenNumber;

  @FXML
  private Label labelAlpha;

  @FXML
  private Canvas canvas;

  @FXML
  void initialize() {
    btnGenerate.setOnAction((event) -> {
      int genNumber = 0;
      try {
        genNumber = Integer.parseInt(txtGenNumber.getText());
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
      if (genNumber > 0) {
        drawShape(canvas.getGraphicsContext2D());
      } else {
        showDialog(ALERT_INVALID_INPUT_NUMBER);
      }
    });
  }

  private void showDialog(int dialog) {
    switch(dialog) {
      case ALERT_INVALID_INPUT_NUMBER:
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Number of vertexes must be a nonnegative integer.");
        alert.showAndWait();
        break;

      default:
        break;
    }
  }

  private void drawShape(GraphicsContext gc) {
    gc.setFill(Color.GREEN);
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(5);
    gc.strokeLine(40, 10, 10, 40);
    gc.fillOval(10, 600, 30, 30);
    gc.strokeOval(60, 60, 30, 30);
  }

}
