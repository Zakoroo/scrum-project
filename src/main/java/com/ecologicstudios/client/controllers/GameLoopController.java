package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.io.IOException;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.utils.Card;
import com.ecologicstudios.utils.Choice;

public class GameLoopController extends BaseController {
    final private String buttonFxml = "/fxml/altButton.fxml"; // path to the button fxml file
    final private GameLoopModel model;
    final private Map<Button, Choice> altMap;
    private Card currentCard;
    private int totalCo2;
 
    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    public GameLoopController() {
        model = GameLoopModel.getInstance();
        altMap = new HashMap<>();
        totalCo2 = 0;
    }

    public void initialize() {
        model.newGame(); // most come first to initialize cards/game
        update();
    }

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
            for (Choice choice : currentCard.getAlternatives()) {
                Button btn = createButton(choice.getChoice());
                btn.setId(String.format("%d", i));
                altMap.put(btn, choice);
                i++;
            }

            // set action for each button in the list
            altMap.keySet().forEach(b -> b.setOnAction((e)->handle_answer(e)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add all buttons to the alternatives area
        altBox.getChildren().addAll(altMap.keySet());
    }

    private void handle_answer(ActionEvent e) {
        System.out.printf("%d\n", altMap.get((Button) e.getSource()).getco2());
        totalCo2 += altMap.get((Button) e.getSource()).getco2();
        
        if (!model.gameEnded()) {
            update();
        } else {
            model.setTotalResult(totalCo2);
            sceneManager.switchScene("/fxml/results.fxml");
        }
    }

    public Button createButton(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        return button;
    }

    public void updateDescription() {
        descriptionLabel.setText(String.format("Description: %s", currentCard.getScenario()));
    }
    
}
