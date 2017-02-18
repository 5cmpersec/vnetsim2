package com.vnetsim2.ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainAppController {

  final int ALERT_INVALID_INPUT_NUMBER = 0;

  @FXML
  private Button btnGenerate;

  @FXML
  private TextField txtGenNumber;

  @FXML
  private Label labelAlpha;

  @FXML
  private Button btnStart;

  @FXML
  private Canvas canvas;

  private GraphRenderer renderer;

  @FXML
  void initialize() {
    renderer = new GraphRenderer(canvas);
    btnGenerate.setOnAction(event -> {
      int genNumber = 0;
      try {
        genNumber = Integer.parseInt(txtGenNumber.getText());
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
      if (genNumber > 0) {
        renderer.generateGraph(genNumber);
        renderer.render(GraphRenderer.Mode.ORIGINAL);
      } else {
        showDialog(ALERT_INVALID_INPUT_NUMBER);
      }
    });

    btnStart.setOnAction(event -> {
      renderer.render(GraphRenderer.Mode.MST);
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

}
