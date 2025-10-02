package com.ecologicstudios.client.controllers;

/**
 * Abstract base class for all JavaFX controllers in the application.
 * <p>
 * This class provides common functionality and structure that all controllers
 * should inherit. It manages the scene manager reference and enforces the
 * implementation of an initialization method in all concrete controllers.
 * 
 * @author Ecologic Studios
 */
public abstract class BaseController {
    /**
     * The scene manager instance used for navigating between different scenes.
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
    public void setSceneManager(SceneManager sceneManager) {
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
    public abstract void initialize();
}