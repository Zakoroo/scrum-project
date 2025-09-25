package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class StartController extends BaseController {

    @FXML
    private Button startBtn;

    public void initialize() {
        
    }

    @FXML
    void handleStart(ActionEvent event) {
        sceneManager.switchScene("/fxml/gameloop.fxml");
    }
}
