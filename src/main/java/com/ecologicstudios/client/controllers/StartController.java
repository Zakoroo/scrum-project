package com.ecologicstudios.client.controllers;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.client.models.SettingsModel;
import com.ecologicstudios.client.models.SettingsModel.Sound;
import com.ecologicstudios.client.models.SettingsModel.Theme;
import com.ecologicstudios.utils.Music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller for the game setup (start) screen.
 * <p>
 * Responsible for presenting and applying initial game configuration
 * (difficulty and number of rounds), toggling UI settings (theme and sound),
 * and navigating to the game or statistics scenes. This class mediates
 * between the FXML view and the {@link com.ecologicstudios.client.models.GameLoopModel}
 * / {@link com.ecologicstudios.client.models.SettingsModel} application state.
 *
 * <p>All public handler methods accept an {@link javafx.event.ActionEvent}
 * produced by the FXML runtime; helper methods encapsulate UI updates.
 *
 * @author Ecologic Studios
 * @version 1.0
 */
public final class StartController extends BaseController {
    // ------------------------------------------------------------------------//
    // external resources
    // ------------------------------------------------------------------------//
    private final String darkButtonImage = "images/darkButton.png";
    private final String lightButtonImage = "images/lightButton.png";
    private final String soundOnImage = "images/sound_on.png";
    private final String soundOffImage = "images/sound_off.png";
    private final String historyImage = "images/history.png";
    private final String gameLoopScene = "/fxml/gameloop.fxml";
    private final String statScene = "/fxml/stat.fxml";

    // ------------------------------------------------------------------------//
    // main fields
    // ------------------------------------------------------------------------//
    /**
     * Reference to the game model for tracking game phases and logic.
     */
    private GameLoopModel model;

    /**
     * Reference to the settings model for setting theme and other GUI settings.
     */    
    private SettingsModel settingsModel;

    /**
     * The currently selected maximum number of cards for the game session.
     */
    private int maxNumCards;

    /**
     * The currently selected difficulty level for the game session.
     */
    private String difficulty;

    // ------------------------------------------------------------------------//
    // fxml elements
    // ------------------------------------------------------------------------//
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

    // ------------------------------------------------------------------------//
    // constructors and initialization
    // ------------------------------------------------------------------------//
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
    * <p>
    * This method is invoked by the JavaFX runtime after the FXML elements have
    * been injected. It also ensures background music is started according to
    * the current settings.
     */
    @Override
    public void initialize() {
        // configure/update buttons
        setRoundsDisabled(maxNumCards, true);
        setDifficultyDisabled(difficulty, true);

        // update music settings
        if (settingsModel.getMusicOn()) {
            Music.playBackground(); 
        }

        // set buttons' icons
        updateThemeButton();
        updateSoundButton();
        updateHistoryButton();
    }

    // ------------------------------------------------------------------------//
    // event handlers
    // ------------------------------------------------------------------------//
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
        this.model.newGame(this.difficulty, this.maxNumCards);
        this.sceneManager.switchScene(gameLoopScene);
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
        setDifficultyDisabled(this.difficulty, false); // enable previous (disabled) difficulty button
        this.difficulty = ((Button) e.getSource()).getText();
        setDifficultyDisabled(this.difficulty, true); // disable current difficulty button
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
        setRoundsDisabled(maxNumCards, false); // enable previous rounds button
        maxNumCards = Integer.parseInt(((Button) e.getSource()).getText());
        setRoundsDisabled(maxNumCards, true); // disable current rounds button
    }

    /**
     * Handles the view history button event.
     * <p>
     * Navigates the user to the statistics view to review their game history and
     * performance data.
     * 
     * @param e the ActionEvent from clicking the history button
     */
    @FXML
    private void handleViewHistory(ActionEvent e) {
        sceneManager.switchScene(statScene);
    }

    /**
     * Handles theme toggle button events.
     * <p>
     * Toggles the application theme between light and dark modes and updates the
     * theme button to reflect the current theme.
     * 
     * @param e the ActionEvent from clicking the theme button
     */
    @FXML
    private void handleTheme(ActionEvent e) {
        toggleTheme();
        applyTheme();
        updateThemeButton();
    }

    private void toggleTheme() {
        settingsModel.toggleTheme();
    }

    /**
     * Handles the sound toggle button event.
     * <p>
     * Toggles the sound setting between on and off, updates the sound button to
     * reflect the current setting, and logs the new sound state.
     * 
     * @param e the ActionEvent from clicking the sound button
     */
    @FXML
    private void handleSound(ActionEvent e) {
        if (settingsModel.getMusicOn()) {
            settingsModel.setMusicOn(false);
            Music.pause();
        } else {
            settingsModel.setMusicOn(true);
            Music.playBackground();
        }

        updateSoundButton();
    }

    // ------------------------------------------------------------------------//
    // button modifiers
    // ------------------------------------------------------------------------//
    /**
     * Sets the disabled state of difficulty buttons based on the difficulty level.
     * <p>
     * This helper method manages the visual state of difficulty selection buttons.
     * 
     * @param text     the difficulty level text ("easy", "medium", or "hard").
     *                 Case-insensitive. If the text does not match any known
     *                 difficulty, the method performs no action.
     * @param disabled true to disable the button, false to enable it
     */
    private void setDifficultyDisabled(String text, Boolean disabled) {
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
     * @param round    the number of rounds (10, 15, or 20). Values outside this
     *                 set will be ignored.
     * @param disabled true to disable the button, false to enable it
     */
    private void setRoundsDisabled(int round, Boolean disabled) {
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
     * Updates the theme button graphic to reflect the current theme.
     */
    private void updateThemeButton() {
        setButtonImage(themeBtn, settingsModel.getTheme() == Theme.LIGHT ? darkButtonImage : lightButtonImage);
    }

    /**
     * Updates the sound button graphic to reflect whether background music is
     * currently playing.
     */
    private void updateSoundButton() {
        setButtonImage(soundBtn, settingsModel.getMusicOn() ? soundOnImage : soundOffImage);
    }

    /**
     * Updates the history button graphic.
     */
    private void updateHistoryButton() {
        setButtonImage(historyBtn, historyImage);
    }

    /**
     * Helper that sets an ImageView on the provided {@link Button} from a
     * classpath resource path.
     *
     * @param btn the button to update; must not be null
     * @param url the relative resource path (e.g. "images/sound_on.png"); the
     *            method looks up a resource using {@code getClass().getResource("/" + url)}.
     *            If the resource cannot be found the method logs to stderr and
     *            returns without modifying the button.
     */
    private void setButtonImage(Button btn, String url) {
        var resource = getClass().getResource("/" + url);
        if (resource == null) {
            System.err.println("Could not find resource: " + url);
            return;
        }

        Image img = new Image(resource.toExternalForm());
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitHeight(45);

        btn.setGraphic(iv);
        btn.setContentDisplay(ContentDisplay.LEFT);
        btn.setGraphicTextGap(8);
    }
}
