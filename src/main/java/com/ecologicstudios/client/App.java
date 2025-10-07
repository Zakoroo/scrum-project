package com.ecologicstudios.client;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.ecologicstudios.client.controllers.SceneManager;
import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.client.models.SettingsModel;

/**
 * Main JavaFX application class for the Climate Game.
 * <p>
 * This class serves as the entry point for the JavaFX application. It initializes
 * the primary stage, sets up the scene manager, initializes game models, and
 * launches the main menu interface.
 * <p>
 * The application presents an interactive game where users make environmental
 * choices and see their impact on CO2 emissions.
 * 
 * @author Ecologic Studios
 */
public class App extends Application {
    /**
     * Initializes and starts the JavaFX application.
     * <p>
     * This method is called by the JavaFX runtime when the application is launched.
     * It sets up the primary stage properties, initializes the game model singleton,
     * creates the scene manager, and displays the main menu.
     * 
     * @param primaryStage the primary stage for this application, onto which
     *                    the application scene can be set
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Climate game");
            primaryStage.setResizable(false);
            Image icon = new Image("gameicon.png");
            primaryStage.getIcons().add(icon);
          
            //Initialize models
            GameLoopModel.getInstance();
            SettingsModel.getInstance();

            // Initialize and create scene manager
            SceneManager.initialize(primaryStage);
            SceneManager.getInstance().switchScene("/fxml/main.fxml");

        } catch (Exception e) {
            System.err.println("Failed to start client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The main entry point for the application.
     * <p>
     * This method launches the JavaFX application by calling the inherited
     * launch method from the Application class.
     * 
     * @param args the command line arguments passed to the application
     */
    public static void main(String[] args) {
        // launch the GUI
        launch(args);
    }
}
