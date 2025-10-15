package com.ecologicstudios.client.controllers;

import java.net.URL;
import javafx.scene.Node;
import com.ecologicstudios.client.models.SettingsModel;

/**
 * Abstract base class for all JavaFX controllers in the application.
 * <p>
 * This class provides common functionality and structure that all controllers
 * should inherit. It manages the scene manager reference and enforces the
 * implementation of an initialization method in all concrete controllers.
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public abstract class BaseController {
    private Node root;

    /**
     * The scene manager instance used for navigating betwen different scenes.
     */
    protected SceneManager sceneManager;

    /**
     * Sets the scene manager for this controller.
     * <p>
     * This method is typically called by the scene manager itself when
     * loading a new controller to provide access to scene navigation functionality.
     * 
     * @param sceneManager the SceneManager instance to be used for scene transitions
     */
    protected void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Initializes the controller after FXML loading.
     * <p>
     * This method is called automatically by JavaFX after the FXML file has been
     * loaded and all @FXML annotated fields have been injected. Concrete
     * implementations should override this method to perform any necessary
     * initialization logic. Make sure to use this method if you wish to inject
     * anything to the scene instead of using the constructor method.
     */
    protected abstract void initialize();

    protected void setRoot(Node root) {
        this.root = root;
    }

    final public void applyTheme() {
        // Remove previous theme
        root.getScene().getStylesheets().removeIf(s -> s.contains("light_theme.css") || s.contains("dark_theme.css"));

        String path = SettingsModel.getInstance().getStylesheet();
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalStateException("Theme CSS not found on classpath: " + path);
        }
        root.getScene().getStylesheets().add(url.toExternalForm());
    }
}