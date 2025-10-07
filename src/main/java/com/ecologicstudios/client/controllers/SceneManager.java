package com.ecologicstudios.client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Singleton class that manages scene transitions and controller lifecycle in the JavaFX application.
 * <p>
 * This class handles loading FXML files, creating scenes, injecting dependencies into controllers,
 * and managing the primary stage. It implements the singleton pattern to ensure consistent
 * scene management throughout the application.
 * <p>
 * The SceneManager automatically injects itself into any controller that extends BaseController
 * and ensures proper initialization of the controller lifecycle.
 * 
 * @author Ecologic Studios
 */
public class SceneManager {
    /**
     * The singleton instance of SceneManager.
     */
    private static SceneManager instance;
    
    /**
     * The primary JavaFX stage for the application.
     */
    private Stage primaryStage;

    /**
     * Initializes the SceneManager singleton with the primary stage.
     * <p>
     * This method should be called once at application startup. Subsequent
     * calls will be ignored and a warning message will be printed.
     * 
     * @param primaryStage the primary JavaFX stage for the application
     */
    public static void initialize(Stage primaryStage) {
        if (instance == null) {
            instance = new SceneManager(primaryStage);
        } else {
            System.out.println("SceneManager is already initialized!");
        }
    }
    
    /**
     * Retrieves the singleton instance of SceneManager.
     * 
     * @return the SceneManager instance
     * @throws IllegalStateException if the SceneManager has not been initialized
     */
    public static SceneManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SceneManager may have not been initialized!");
        }
        return instance;
    }

    /**
     * Private constructor for the SceneManager singleton.
     * 
     * @param primaryStage the primary JavaFX stage to be managed
     */
    private SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Switches to a new scene by loading the specified FXML file.
     * <p>
     * This method performs the following operations:
     * <ul>
     * <li>Loads the FXML file using FXMLLoader</li>
     * <li>Creates a new Scene from the loaded content</li>
     * <li>Injects the SceneManager into the controller if it extends BaseController</li>
     * <li>Sets the new scene on the primary stage and displays it</li>
     * </ul>
     * If an IOException occurs during loading, the error is printed to the console.
     * 
     * @param fxmlFile the path to the FXML file to load (relative to the classpath)
     */
    public void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());

            // Get the controller and inject SceneManager
            Object controller = loader.getController();
            if (controller instanceof BaseController && controller != null) {
                BaseController baseController = (BaseController) controller;
                baseController.setSceneManager(this);
                baseController.applyTheme();
            }

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
