package com.ecologicstudios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientApp extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Model model = new Model();
    Controller controller = new Controller(model);
    MainView mainview = new MainView();


  /*
   * 
   * Label label = new Label("Hello, JavaFX!");
    StackPane root = new StackPane(label);
    Scene scene = new Scene(root, 400, 200);

    primaryStage.setTitle("JavaFX Hello World");
    primaryStage.setScene(scene);
    primaryStage.show();
   */
    
  }

}
