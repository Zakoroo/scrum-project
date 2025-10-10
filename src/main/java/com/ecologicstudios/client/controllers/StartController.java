package com.ecologicstudios.client.controllers;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.client.models.SettingsModel;
import com.ecologicstudios.client.models.SettingsModel.Sound;
import com.ecologicstudios.client.models.SettingsModel.Theme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller class for managing the game setup interface.
 * <p>
 * This controller handles the configuration of game settings including
 * difficulty level and number of rounds before starting the actual game. It
 * provides an interactive interface for users to customize their gaming
 * experience and validates their selections before starting the game.
 * 
 * @author Ecologic Studios
 */
public class StartController extends BaseController {
    /**
     * Reference to the game model for setting configuration options.
     */
    private GameLoopModel model;

    private SettingsModel settingsModel;

    /**
     * The currently selected maximum number of cards for the game session.
     */
    private int maxNumCards;

    /**
     * The currently selected difficulty level for the game session.
     */
    private String difficulty;

    @FXML
    private HBox root;

    @FXML
    private Button historyBtn;

    @FXML
    private Button themeBtn;

    @FXML
    private Button soundBtn;

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
     * <p>
     * Retrieves the current game configuration from the model to set initial values
     * for difficulty and maximum number of cards.
     */
    public StartController() {
        this.model = GameLoopModel.getInstance();
        this.maxNumCards = model.getMaxNumCards();
        this.difficulty = model.getDifficulty();
        this.settingsModel = SettingsModel.getInstance();
    }

    /**
     * Initializes the controller after FXML loading.
     * <p>
     * Sets the initial visual state of buttons based on current game configuration,
     * disabling the buttons that correspond to the currently selected difficulty
     * and number of rounds.
     */
    public void initialize() {
        setRoundsDisable(maxNumCards, true);
        setDifficultyDisable(difficulty, true);
        setRoot((Node) this.root);
        updateThemeButton();
        updateSoundButton();
        updateHistoryButton();
    }

    /**
     * Handles difficulty selection button events.
     * <p>
     * Updates the visual state of difficulty buttons and stores the selected
     * difficulty locally. The previously selected button is enabled and the
     * newly selected button is disabled to show current selection.
     * 
     * @param e the ActionEvent from clicking a difficulty button
     */
    @FXML
    private void handleDifficulty(ActionEvent e) {
        setDifficultyDisable(difficulty, false); // enable previous (disabled) difficulty button
        difficulty = ((Button) e.getSource()).getText();
        setDifficultyDisable(difficulty, true); // disable current difficulty button
    }

    @FXML
    public void handleTheme(ActionEvent e) {
        toggleTheme();
        applyTheme();
        updateThemeButton();
    }

    public void toggleTheme() {
        settingsModel.toggleTheme();
    }


    /**
     * Handles round selection button events.
     * <p>
     * Updates the visual state of round buttons and stores the selected number
     * of rounds locally. The previously selected button is enabled and the
     * newly selected button is disabled to show current selection.
     * 
     * @param e the ActionEvent from clicking a rounds button
     */
    @FXML
    private void handleRounds(ActionEvent e) {
        setRoundsDisable(maxNumCards, false); // enable previous rounds button
        maxNumCards = Integer.parseInt(((Button) e.getSource()).getText());
        setRoundsDisable(maxNumCards, true); // disable current rounds button
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

    private void updateThemeButton() {
        Image img = new Image(
                settingsModel.getTheme() == Theme.LIGHT ? "darkButton.png" : "lightButton.png");
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitHeight(45);

        themeBtn.setGraphic(iv);
        themeBtn.setContentDisplay(ContentDisplay.LEFT);
        themeBtn.setGraphicTextGap(8);
    }

    private void updateSoundButton() {
        Image img = new Image(
                SettingsModel.getInstance().getSound() == Sound.ON ? "sound_on.png" : "sound_off.png");
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitHeight(45);

        soundBtn.setGraphic(iv);
        soundBtn.setContentDisplay(ContentDisplay.LEFT);
        soundBtn.setGraphicTextGap(8);
    }

    private void updateHistoryButton() {
        Image img = new Image("history.png");
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitHeight(45);

        historyBtn.setGraphic(iv);
        historyBtn.setContentDisplay(ContentDisplay.LEFT);
        historyBtn.setGraphicTextGap(8);
    }

    /**
     * Handles the start game button event.
     * <p>
     * Applies the selected game configuration to the model, starts a new game,
     * and transitions from the setup screen to the main game loop interface.
     * 
     * @param e the ActionEvent from clicking the start button
     */
    @FXML
    private void handleStart(ActionEvent e) {
        model.newGame(difficulty, maxNumCards);
        sceneManager.switchScene("/fxml/gameloop.fxml");
    }

    @FXML
    private void handleViewHistory(ActionEvent e) {
        sceneManager.switchScene("/fxml/stat.fxml");
    }

    @FXML
    private void handleSound(ActionEvent e) {
        settingsModel.toggleSound();
        updateSoundButton();
        System.out.println(String.format("Sound is: %s", settingsModel.getSound()));
    }

}
