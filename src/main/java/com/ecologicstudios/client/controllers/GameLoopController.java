package com.ecologicstudios.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.*;
import java.io.IOException.*;

public class GameLoopController {

    final private String buttonFxml = "/fxml/altButton.fxml";
 
    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox altBox;

    private List<Button> buttonlist;

    public void initialize() {
        buttonlist = new LinkedList<>();

        try {
            buttonlist.add(createButton("hello"));
            buttonlist.add(createButton("world"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Button b : buttonlist) {
            altBox.getChildren().add(b);
        }
    }

    public Button createButton(String text) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonFxml));
        Button button = loader.load();
        button.setText(text);
        return button;
    }
}
