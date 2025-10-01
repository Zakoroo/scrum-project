package com.ecologicstudios.client.controllers;

import com.ecologicstudios.client.models.GameLoopModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController extends BaseController {

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


    public StartController() {
        this.model = GameLoopModel.getInstance();
    }

    public void initialize() {
        easyBtn.setDisable(true);
        roundsBtn1.setDisable(true);
    }

    @FXML
    void handleDifficulty(ActionEvent e) {
        // enable previous difficulty button
        setDifficultyDisable(model.getDifficulty(), false);

        // disable current difficulty button
        String buttonText = ((Button) e.getSource()).getText();

        setDifficultyDisable(buttonText, true);
        
        model.setDifficulty(buttonText);
    }

    @FXML
    void handleRounds(ActionEvent e) {
        // enable previous rounds button
        setRoundsDisable(model.getMaxNumCards(), false);

        // disable current round button
        int round = Integer.parseInt(((Button) e.getSource()).getText());

        setRoundsDisable(round, true);

        model.setMaxNumCards(round);

    }


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

    @FXML
    void handleStart(ActionEvent e) {
        sceneManager.switchScene("/fxml/gameloop.fxml");
    }
}
