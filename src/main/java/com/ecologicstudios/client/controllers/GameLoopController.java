package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.utils.Card;
import com.ecologicstudios.utils.Alternative;

/**
 * Controller class for managing the main game loop interface.
 * Handles the display of cards, alternatives, and user interactions during gameplay.
 * 
 * @author EcoLogic Studios
 */
public class GameLoopController extends BaseController {
    /**
     * Path to the FXML file for alternative buttons.
     */
    final private String buttonFxml = "/fxml/altButton.fxml";
    
    /**
     * The game model instance that manages game state and logic.
     */
    final private GameLoopModel model;
    
    /**
     * Map that associates each alternative button with its corresponding Alternative object.
     */
    final private Map<Button, Alternative> altMap;
    
    /**
     * The current card being displayed to the user.
     */
    private Card currentCard;

    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    /**
     * Constructs a new GameLoopController and initializes the model and alternative map.
     */
    public GameLoopController() {
        model = GameLoopModel.getInstance();
        altMap = new HashMap<>();
    }

    /**
     * Initializes the controller after FXML loading.
     * Sets up a new game and updates the UI with the first card.
     */
    public void initialize() {
        model.newGame(); // most come first to initialize cards/game
        update();
    }

    /**
     * Updates the game interface with the next card and its alternatives.
     * Clears previous alternatives, fetches the next card, and creates new alternative buttons.
     * If no more cards are available, transitions to the results screen.
     */
    public void update() {
        try {
            // update current card
            currentCard = model.nextCard();

            // update description
            updateDescription();

            // clear alternatives and altMap
            altBox.getChildren().clear();
            altMap.clear();

            // fetch alternatives from model
            int i = 0;
            for (Alternative choice : currentCard.getAlternatives()) {
                Button btn = createButton(choice.getChoice());
                btn.setId(String.format("%d", i));
                altMap.put(btn, choice);
                i++;
            }

            // set action for each button in the list
            altMap.keySet().forEach(b -> b.setOnAction((e) -> handleAnswer(e)));
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        // add all buttons to the alternatives area
        altBox.getChildren().addAll(altMap.keySet());
    }

    /**
     * Handles user selection of an alternative answer.
     * Submits the selected answer to the model and either updates to the next card
     * or transitions to the results screen if the game has ended.
     * 
     * @param e the ActionEvent triggered by clicking an alternative button
     */
    private void handleAnswer(ActionEvent e) {
        model.submitAnswer(altMap.get((Button) e.getSource()));

        if (!model.gameEnded()) {
            update();
        } else {
            sceneManager.switchScene("/fxml/results.fxml");
        }
    }

    /**
     * Creates a new button for an alternative choice by loading it from FXML.
     * 
     * @param text the text to display on the button
     * @return a configured Button with the specified text and styling
     * @throws IOException if the FXML file cannot be loaded
     */
    public Button createButton(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        button.getStyleClass().addAll("setting", "alternatives"); // add style class to button

        return button;
    }

    /**
     * Updates the description label with the current card's scenario text.
     */
    public void updateDescription() {
        descriptionLabel.setText(String.format("Description: %s", currentCard.getScenario()));
    }
}
