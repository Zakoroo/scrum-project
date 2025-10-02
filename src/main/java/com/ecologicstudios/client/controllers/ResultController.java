package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.ecologicstudios.client.models.GameLoopModel;

/**
 * Controller class for managing the game results interface.
 * <p>
 * This controller displays the final CO2 emission results after a game session
 * has been completed and provides navigation back to the main menu.
 * 
 * @author Ecologic Studios
 */
public class ResultController extends BaseController {
    /**
     * Reference to the game model for retrieving final results.
     */
    private GameLoopModel model;

    @FXML
    private Button returnID;

    @FXML
    private Label co2ID;

    /**
     * Constructs a new ResultController and initializes the game model reference.
     */
    public ResultController() {
        model = GameLoopModel.getInstance();
    }

    /**
     * Initializes the controller after FXML loading.
     * <p>
     * Retrieves the total CO2 result from the game model and displays it on the
     * results screen.
     */
    public void initialize() {

        // Add results to result screen.
        updateCO2(String.format("%d", model.getTotalResult()));
    }

    /**
     * Handles the return to main menu button event.
     * <p>
     * Navigates the user back to the main menu interface after viewing results.
     * 
     * @param event the ActionEvent from clicking the return button
     */
    @FXML
    private void handleReturn(ActionEvent event) {
        sceneManager.switchScene("/fxml/main.fxml");
    }

    /**
     * Updates the CO2 display label with the provided value.
     * <p>
     * Formats and displays the CO2 emission value on the results screen.
     * 
     * @param co2 the CO2 emission value as a string to display
     */
    private void updateCO2(String co2) {
        co2ID.setText(String.format("Co2: %s", co2));
    }
}
