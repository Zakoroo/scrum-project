package com.ecologicstudios.client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.ecologicstudios.client.controllers.*;

public class SceneManager {
    private static SceneManager instance;
    private BaseController currentController;
    private Stage primaryStage;

    public static void initialize(Stage primaryStage) {
        if (instance == null) {
            instance = new SceneManager(primaryStage);
        } else {
            System.out.println("SceneManager is already initialized!");
        }
    }
    
    public static SceneManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SceneManager may have not been initialized!");
        }
        return instance;
    }

    private SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());

            // Get the controller and inject SceneManager
            Object controller = loader.getController();
            if (controller instanceof BaseController && controller != null) {
                BaseController baseController = (BaseController) controller;
                baseController.setSceneManager(this);
                baseController.initialize();
                currentController = baseController;
            }

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BaseController getCurrenController() {
        return this.currentController;
    }

    public void setCurrenController(BaseController currenController) {
        this.currentController = currenController;
    }
}
