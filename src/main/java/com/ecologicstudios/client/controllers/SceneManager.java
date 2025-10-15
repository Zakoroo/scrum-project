package com.ecologicstudios.client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import com.ecologicstudios.client.models.AppContext;

/**
 * Singleton class that manages scene transitions and controller lifecycle in
 * the JavaFX application.
 * <p>
 * This class handles loading FXML files, creating scenes, injecting
 * dependencies into controllers,
 * and managing the primary stage. It implements the singleton pattern to ensure
 * consistent
 * scene management throughout the application.
 * <p>
 * The SceneManager automatically injects itself into any controller that
 * extends BaseController
 * and ensures proper initialization of the controller lifecycle.
 * 
 * @author Ecologic Studios
 * @version 1.0
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
     * Reference to the context object through which access to models and
     * configurations is managed.
     */
    private AppContext context;

    /**
     * Initializes the SceneManager singleton with the primary stage.
     * <p>
     * This method should be called once at application startup. Subsequent
     * calls will be ignored and a warning message will be printed.
     * 
     * @param primaryStage the primary JavaFX stage for the application
     * @param context
     */
    public static void init(Stage primaryStage, AppContext context) {
        if (instance == null)
            instance = new SceneManager(primaryStage, context);
    }

    /**
     * Retrieves the singleton instance of SceneManager.
     * 
     * @return the SceneManager instance
     * @throws IllegalStateException if the SceneManager has not been initialized
     */
    public static SceneManager getInstance() {
        if (instance == null)
            throw new IllegalStateException("SceneManager may have not been initialized!");
        return instance;
    }

    /**
     * Private constructor for the SceneManager singleton.
     * 
     * @param primaryStage the primary JavaFX stage to be managed
     * @param context
     */
    private SceneManager(Stage primaryStage, AppContext context) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "primaryStage is null");
        this.context = Objects.requireNonNull(context, "context is null");
    }

    /**
     * Switches to a new scene by loading the specified FXML file.
     * <p>
     * This method performs the following operations:
     * <ul>
     * <li>Loads the FXML file using FXMLLoader</li>
     * <li>Creates a new Scene from the loaded content</li>
     * <li>Injects the SceneManager into the controller if it extends
     * BaseController</li>
     * <li>Sets the new scene on the primary stage and displays it</li>
     * </ul>
     * If an IOException occurs during loading, the error is printed to the console.
     * 
     * @param fxmlFile the path to the FXML file to load (relative to the classpath)
     */
    public void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the controller and inject SceneManager
            Object controller = loader.getController();
            Scene scene = new Scene(root);

            if (controller instanceof BaseController baseController) {
                baseController.setSceneManager(this);
                baseController.setContext(this.context);
                baseController.setRoot(root);
                baseController.onReady();
                baseController.applyTheme();
            }

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppContext getContext() {
        return this.context;
    }
}
