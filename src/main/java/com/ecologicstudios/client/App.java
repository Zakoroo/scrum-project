package com.ecologicstudios.client;

import javafx.application.Application;
import javafx.stage.Stage;
import com.ecologicstudios.client.utils.*;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Climate game");

            // Initialize and create scene manager
            SceneManager.initialize(primaryStage);
            SceneManager.getInstance().switchScene("/fxml/main.fxml");

        } catch (Exception e) {
            System.err.println("Failed to start client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // launch the GUI
        launch(args);
    }
}
