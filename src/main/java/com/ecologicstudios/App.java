package com.ecologicstudios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception{

    //Load fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
    Parent root = loader.load();


    //Create the scene using the FXML root
    Scene scene = new Scene(root);

    primaryStage.setTitle("My javaFX App");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

}
