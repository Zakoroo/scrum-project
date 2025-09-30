package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.ecologicstudios.client.models.GameLoopModel;

public class ResultController extends BaseController {
    private GameLoopModel model;

    @FXML
    private Button exitID;

    @FXML
    private Label co2ID;

    public ResultController() {
        model = GameLoopModel.getInstance();
    }

    public void initialize() {
        
        // Add results to result screen.
        updateCO2(String.format("%d", model.getTotalResult()));
    }

    @FXML
    void handleExit(ActionEvent event) {
        sceneManager.switchScene("/fxml/main.fxml");
    }

    
    public void updateCO2(String co2) {
        co2ID.setText(String.format("Co2: %s", co2));
    }


}
