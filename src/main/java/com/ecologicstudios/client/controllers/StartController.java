package com.ecologicstudios.client.controllers;

import com.ecologicstudios.client.models.GameLoopModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller class for managing the game setup interface.
 * <p>
 * This controller handles the configuration of game settings including
 * difficulty level and number of rounds before starting the actual game. It
 * provides an interactive interface for users to customize their gaming
 * experience.
 * 
 * @author Ecologic Studios
 */
public class StartController extends BaseController {
    /**
     * Reference to the game model for setting configuration options.
     */
    private GameLoopModel model;

    @FXML
    private Button handleDifficulty;

    @FXML
    private Button handleRounds;

    @FXML
    private Button easyBtn;

    @FXML
    private Button mediumBtn;

    @FXML
    private Button hardBtn;

    @FXML
    private Button roundsBtn1;

    @FXML
    private Button roundsBtn2;

    @FXML
    private Button roundsBtn3;

    @FXML
    private Button startBtn;

    /**
     * Constructs a new StartController and initializes the game model reference.
     */
    public StartController() {
        this.model = GameLoopModel.getInstance();
    }

    /**
     * Initializes the controller after FXML loading. Sets default selections for
     * difficulty (Easy) and rounds (10).
     */
    public void initialize() {
        setDifficultyDisable(model.getDifficulty(), true);
        setRoundsDisable(model.getMaxNumCards(), true);
    }

    /**
     * Handles difficulty selection button events.
     * <p>
     * Updates the visual state of difficulty buttons and sets the selected
     * difficulty in the game model. The previously selected button is enabled and
     * the newly selected button is disabled to show current selection.
     * 
     * @param e the ActionEvent from clicking a difficulty button
     */
    @FXML
    private void handleDifficulty(ActionEvent e) {
        // enable previous difficulty button
        setDifficultyDisable(model.getDifficulty(), false);

        // disable current difficulty button
        String buttonText = ((Button) e.getSource()).getText();

        setDifficultyDisable(buttonText, true);

        model.setDifficulty(buttonText);
    }

    /**
     * Handles round selection button events.
     * <p>
     * Updates the visual state of round buttons and sets the selected number of
     * rounds in the game model. The previously selected button is enabled and the
     * newly selected button is disabled to show current selection.
     * 
     * @param e the ActionEvent from clicking a rounds button
     */
    @FXML
    private void handleRounds(ActionEvent e) {
        // enable previous rounds button
        setRoundsDisable(model.getMaxNumCards(), false);

        // disable current round button
        int round = Integer.parseInt(((Button) e.getSource()).getText());

        setRoundsDisable(round, true);

        model.setMaxNumCards(round);

    }

    /**
     * Sets the disabled state of difficulty buttons based on the difficulty level.
     * <p>
     * This helper method manages the visual state of difficulty selection buttons.
     * 
     * @param text     the difficulty level text ("easy", "medium", or "hard")
     * @param disabled true to disable the button, false to enable it
     */
    private void setDifficultyDisable(String text, Boolean disabled) {
        switch (text.toLowerCase()) {
        case "easy":
            easyBtn.setDisable(disabled);
            break;
        case "medium":
            mediumBtn.setDisable(disabled);
            break;
        case "hard":
            hardBtn.setDisable(disabled);
        }
    }

    /**
     * Sets the disabled state of round buttons based on the round number.
     * <p>
     * This helper method manages the visual state of round selection buttons.
     * 
     * @param round    the number of rounds (10, 15, or 20)
     * @param disabled true to disable the button, false to enable it
     */
    private void setRoundsDisable(int round, Boolean disabled) {
        switch (round) {
        case 10:
            roundsBtn1.setDisable(disabled);
            break;
        case 15:
            roundsBtn2.setDisable(disabled);
            break;
        case 20:
            roundsBtn3.setDisable(disabled);
        }
    }

    /**
     * Handles the start game button event.
     * <p>
     * Transitions from the setup screen to the main game loop interface.
     * 
     * @param e the ActionEvent from clicking the start button
     */
    @FXML
    private void handleStart(ActionEvent e) {
        sceneManager.switchScene("/fxml/gameloop.fxml");
    }
}
