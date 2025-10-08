package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.utils.GameHistory;
import com.ecologicstudios.utils.GameSession;

public class StatController extends BaseController {
    private final String historyItemPath = "/fxml/historyItem.fxml";
    private GameLoopModel model;

    @FXML
    private HBox root;

    @FXML
    private Button returnBtn;

    @FXML
    private VBox historyList;

    @FXML
    private LineChart<?, ?> lineChart;

    public void initialize() {
        this.model = GameLoopModel.getInstance();
        updateHistory();
        setRoot((Node) root);
    }

    public void updateHistory() {
        List<GameSession> gameSessions = model.getHistory().getAllSessions();
        try {
            for (GameSession session : gameSessions) {
                addHistoryItem(session);
            }
        } catch (IOException e) {
            System.err.println("Some error unexpected occurred while loading history items: " + e.getMessage());
        }
    }

    public void updateChart() {

    }

    private void addHistoryItem(GameSession session) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(historyItemPath));
        HBox histItem = loader.load();
        Label label = (Label) histItem.getChildren().get(0);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Date: %s\n", session.getTimestamp()));
        stringBuilder.append(String.format("Difficulty: %s\n", session.getDifficulty()));
        stringBuilder.append(String.format("Number rounds: %d\n", session.getTotalRounds()));
        stringBuilder.append(String.format("Best score: %.2f kg\n", session.getBestScore()));
        stringBuilder.append(String.format("Worst score: %.2f kg\n", session.getWorstScore()));
        stringBuilder.append(String.format("Your score: %.2f kg\n", session.getTotalScore()));

        label.setText(stringBuilder.toString());

        historyList.getChildren().add(histItem);
    }

    /**
     * Handles the return to main menu button event.
     * <p>
     * Navigates the user back to the main menu interface after viewing their
     * statistics and performance insight.
     * 
     * @param event the ActionEvent from clicking the return button
     */
    @FXML
    private void handleReturn(ActionEvent event) {
        sceneManager.switchScene("/fxml/main.fxml");
    }

}
