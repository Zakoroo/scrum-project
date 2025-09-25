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

public class GameLoopController {
    final private String buttonFxml = "/fxml/altButton.fxml"; // path to the button fxml file
    final private GameLoopModel model;
    final private Map<String, String> altMap;
 
    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    private List<Button> buttonList;

    public GameLoopController() {
        model = GameLoopModel.getInstance();
        altMap = new HashMap<>();
        buttonList = new LinkedList<>();
    }

    public void initialize() {
        update();
    }

    public void update() {
        try {
            // update description
            //updateDescription();
            
            // clear alternatives
            altBox.getChildren().clear();

            // fetch alternatives from model
            buttonList.add(createButton("alternative 1"));
            buttonList.add(createButton("alternative 2"));
            
            // set action for each button in the list
            buttonList.forEach(b -> b.setOnAction((e)->handle_answer(e)));

            // assign different IDs to all the buttons
            int i = 0;
            for (Button b : buttonList) {
                b.setId(String.format("%d", i));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add all buttons to the alternatives area
        altBox.getChildren().addAll(buttonList);
    }

    private void handle_answer(ActionEvent e) {
        System.out.printf("%s\n", ((Button) e.getSource()).getId());
    }

    public Button createButton(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        return button;
    }

    public void updateDescription(String desc) {
        descriptionLabel.setText(String.format("Description: %s", desc));
    }
    
}
