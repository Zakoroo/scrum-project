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
import javafx.scene.layout.BorderPane;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.utils.Card;
import com.ecologicstudios.utils.Alternative;

/**
 * Controller class for managing the main game loop interface.
 * <p>
 * This controller handles the display of environmental scenario cards, their alternative
 * choices, and user interactions during gameplay. It manages the game flow by presenting
 * cards sequentially and processing user selections until the game ends.
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public final class GameLoopController extends BaseController {
    // ------------------------------------------------------------------------//
    // external resources
    // ------------------------------------------------------------------------//
    final private String buttonFxml = "/fxml/altButton.fxml";
    
    // ------------------------------------------------------------------------//
    // main fields
    // ------------------------------------------------------------------------//
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

    // ------------------------------------------------------------------------//
    // fxml elements
    // ------------------------------------------------------------------------//
    @FXML
    private BorderPane root;

    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    // ------------------------------------------------------------------------//
    // constructors and initialization
    // ------------------------------------------------------------------------//
    /**
     * Constructs a new GameLoopController and initializes the model and alternative map.
     */
    public GameLoopController() {
        model = GameLoopModel.getInstance();
        altMap = new HashMap<>();
    }

    /**
     * Initializes the controller after FXML loading.
     * <p>
     * This method is called automatically by JavaFX and starts the game loop
     * by updating the interface with the first card.
     */
    public void initialize() {
        update();
    }

    // ------------------------------------------------------------------------//
    // event handlers
    // ------------------------------------------------------------------------//
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

    // ------------------------------------------------------------------------//
    // update UI with next question & alternatives
    // ------------------------------------------------------------------------//
    /**
     * Updates the game interface with the next card and its alternatives.
     * <p>
     * This method fetches the next card from the game model, clears previous alternatives,
     * creates new alternative buttons, and updates the display. If no more cards are
     * available or the game has ended, appropriate error handling is performed.
     */
    private void update() {
        try {
            // Update current card
            currentCard = model.nextCard();

            // Update description
            updateDescription();

            // Clear alternatives and altMap
            altBox.getChildren().clear();
            altMap.clear();

            // Fetch alternatives from model
            int i = 0;
            for (Alternative choice : currentCard.getAlternatives()) {
                Button btn = createButton(choice.getChoice());
                btn.setId(String.format("%d", i));
                altMap.put(btn, choice);
                i++;
            }

            // Set action for each button in the list
            altMap.keySet().forEach(btn -> btn.setOnAction((e) -> handleAnswer(e)));
            
            // Add all buttons to the alternatives box
            altBox.getChildren().addAll(altMap.keySet());
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Creates a new styled button for an alternative choice by loading it from FXML.
     * <p>
     * The button is loaded from a separate FXML file and configured with the provided
     * text and appropriate CSS style classes for consistent appearance.
     * 
     * @param text the text to display on the button
     * @return a configured Button with the specified text and styling
     * @throws IOException if the FXML file cannot be loaded or parsed
     */
    private Button createButton(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        

        return button;
    }

    /**
     * Updates the description label with the current card's scenario text.
     * <p>
     * Formats and displays the environmental scenario description for the current card.
     */
    private void updateDescription() {
        descriptionLabel.setText(String.format("Description: %s", currentCard.getScenario()));
    }
}
