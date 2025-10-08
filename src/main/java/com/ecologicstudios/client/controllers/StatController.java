package com.ecologicstudios.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.ecologicstudios.utils.GameHistory;

public class StatController extends BaseController {
    private final String historyPath = "resources/json/history.json";
    private GameHistory history;

    @FXML
    private HBox root;

    @FXML
    private VBox historyList;

    @FXML
    private LineChart<?, ?> lineChart;

    public void initialize() {

    } 

    public void updateHistory() {

    }

    public void updateChart() {

    }
}
