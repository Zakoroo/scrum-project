package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.*;
import java.io.IOException;

public class GameLoopController {
    final private String buttonFxml = "/fxml/altButton.fxml"; // path to the button fxml file
 
    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    private List<Button> buttonlist;

    public void initialize() {
        buttonlist = new LinkedList<>();

        try {
            // add buttons to the list
            buttonlist.add(createButton("alternative 1"));
            buttonlist.add(createButton("alternative 2"));

            // set action for each button in the list
            buttonlist.forEach(b -> b.setOnAction((e)->handle_answer(e)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add all buttons to the alternatives area
        altBox.getChildren().addAll(buttonlist);
    }

    private void handle_answer(ActionEvent e) {
        System.out.println("Hello");
    }

    public Button createButton(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        return button;
    }
}
