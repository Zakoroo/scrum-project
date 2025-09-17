package com.ecologicstudios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Label label = new Label("Hello, JavaFX!");
    StackPane root = new StackPane(label);
    Scene scene = new Scene(root, 400, 200);

    primaryStage.setTitle("JavaFX Hello World");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public int add(int a, int b) {
    return a + b;
  }
}
