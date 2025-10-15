package com.ecologicstudios.client.controllers;

import java.net.URL;
import java.util.Objects;

import javafx.scene.Node;

import com.ecologicstudios.client.models.AppContext;
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

    private AppContext context;

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
    protected final void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    protected final void setRoot(Node root) {
        this.root = root;
    }

    public final void setContext(AppContext context) {
        this.context = Objects.requireNonNull(context, "context is null");
    } 

    protected final AppContext getContext() {
        return this.context;
    }


    public final void applyTheme() {
        if (root == null || root.getScene() == null) return;

        // Remove previous theme
        root.getScene().getStylesheets().removeIf(s -> s.contains("light_theme.css") || s.contains("dark_theme.css"));

        String path = SettingsModel.getInstance().getStylesheet();
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalStateException("Theme CSS not found on classpath: " + path);
        }
        root.getScene().getStylesheets().add(url.toExternalForm());
    }

    protected void onReady() {}
}