package com.vnetsim2.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Scene scene = new Scene(FXMLLoader.<Parent>load(MainApp.class.getResource("main_app.fxml")), 1080, 668);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
