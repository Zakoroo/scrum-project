package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import com.ecologicstudios.client.models.GameLoopModel;

/**
 * Controller class for managing the game results interface.
 * <p>
 * This controller displays the final CO2 emission results and feedback after a game session
 * has been completed. It provides navigation back to the main menu and presents the
 * environmental impact summary of the player's choices.
 * 
 * @author Ecologic Studios
 */
public final class ResultController extends BaseController {
    // ------------------------------------------------------------------------//
    // external resources
    // ------------------------------------------------------------------------//
    private final String mainScene = "/fxml/main.fxml";

    // ------------------------------------------------------------------------//
    // main fields
    // ------------------------------------------------------------------------//
    /**
     * Reference to the game model for retrieving final results.
     */
    private GameLoopModel model;

    // ------------------------------------------------------------------------//
    // fxml elements
    // ------------------------------------------------------------------------//
    @FXML
    private BorderPane root;

    @FXML
    private Button returnID;

    @FXML
    private Label co2ID;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Label resultLabel;

    // ------------------------------------------------------------------------//
    // constructors and initialization
    // ------------------------------------------------------------------------//
    /**
     * Constructs a new ResultController and initializes the game model reference.
     */
    public ResultController() {
        model = GameLoopModel.getInstance();
    }

    /**
     * Initializes the controller after FXML loading.
     * <p>
     * Retrieves the total CO2 result and feedback from the game model and displays
     * them on the results screen with appropriate formatting.
     */
    public void initialize() {
        updateCO2(String.format("%.2f", model.getTotalResult())); // Add results to result screen.
        updateFeedback(model.getFeedback(), model.getscoreTitle());
        setRoot((Node) this.root);
    }

    // ------------------------------------------------------------------------//
    // event handlers
    // ------------------------------------------------------------------------//
    /**
     * Handles the return to main menu button event.
     * <p>
     * Navigates the user back to the main menu interface after viewing their
     * game results and environmental impact summary.
     * 
     * @param event the ActionEvent from clicking the return button
     */
    @FXML
    private void handleReturn(ActionEvent event) {
        sceneManager.switchScene(mainScene);
    }

    // ------------------------------------------------------------------------//
    // update labels
    // ------------------------------------------------------------------------//
    /**
     * Updates the CO2 display label with the provided emission value.
     * <p>
     * Formats and displays the total CO2 emission value in kilograms on the results screen.
     * 
     * @param co2 the CO2 emission value as a string to display
     */
    private void updateCO2(String co2) {
        co2ID.setText(String.format("Co2: %s kg", co2));
    }

    /**
     * Updates the feedback label with environmental impact assessment.
     * <p>
     * Displays personalized feedback based on the player's choices and their
     * environmental impact during the game session.
     * 
     * @param feedback the feedback message to display to the player
     */
    private void updateFeedback(String feedback, String scoretitle) {
        feedbackLabel.setText(String.format("Feedback: %s", feedback));
        resultLabel.setText(String.format("Result: %s", scoretitle));
    }
}
