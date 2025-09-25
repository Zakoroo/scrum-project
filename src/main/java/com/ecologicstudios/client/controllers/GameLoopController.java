package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

import com.ecologicstudios.client.models.GameLoopModel;

public class GameLoopController {
    final private String buttonFxml = "/fxml/altButton.fxml"; // path to the button fxml file
    final private GameLoopModel model;
 
    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    private List<Button> buttonList;

    public GameLoopController() {
        model = new GameLoopModel();
    }

    public void initialize() {
        buttonList = new LinkedList<>();

        try {
            // add buttons to the list
            buttonList.add(createButton("alternative 1"));
            buttonList.add(createButton("alternative 2"));

            // set action for each button in the list
            buttonList.forEach(b -> b.setOnAction((e)->handle_answer(e)));

            // assign different IDs to all the buttons
            int i = 0;
            for (Button b : buttonList) {
                b.setId(String.format("altBtn%d", i));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add all buttons to the alternatives area
        altBox.getChildren().addAll(buttonList);
    }

    private void handle_answer(ActionEvent e) {
        if (!model.gameEnded()) {
            System.out.printf("value: %d\n", model.increment());;
        }
        else {
            System.out.println("game ended!");
        }
    }

    public Button createButton(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        return button;
    }
}
