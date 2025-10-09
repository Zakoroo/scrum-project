package com.ecologicstudios.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.utils.ChartBuilder;
import com.ecologicstudios.utils.GameHistory;
import com.ecologicstudios.utils.GameSession;
import com.ecologicstudios.utils.PerformanceCalculator;
import com.ecologicstudios.utils.StandardDeviationCalculator;

public class StatController extends BaseController {
    private final String historyItemPath = "/fxml/historyItem.fxml";
    private GameLoopModel model;
    private List<GameSession> gameSessions;
    private List<XYChart.Data<Number, Number>> points;

    @FXML
    private HBox root;

    @FXML
    private Button returnBtn;

    @FXML
    private VBox historyList;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private Label performanceLabel;

    public void initialize() {
        this.model = GameLoopModel.getInstance();
        this.gameSessions = model.getHistory().getAllSessions();

        if (!this.gameSessions.isEmpty()) {
            this.points = new ChartBuilder(gameSessions, new PerformanceCalculator()).getChartData();
            updateChart();
            updateHistory();
        }

        updatePerformanceLabel();
        setRoot((Node) root);
    }

    public void updateHistory() {
        try {
            for (GameSession session : gameSessions) {
                addHistoryItem(session);
            }
        } catch (IOException e) {
            System.err.println("Some error unexpected occurred while loading history items: " + e.getMessage());
        }
    }

    public void updateChart() {
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();

        // Set axis labels
        xAxis.setLabel("Game Number");
        yAxis.setLabel("Score (%)");

        // Style the y-axis label directly - move it left
        Node yAxisLabel = yAxis.lookup(".axis-label");
        if (yAxisLabel != null) {
            yAxisLabel.setStyle("-fx-padding: -20px 0px 0px 0px;");
        }

        // Keep x-axis label normal
        Node xAxisLabel = xAxis.lookup(".axis-label");
        if (xAxisLabel != null) {
            xAxisLabel.setStyle("-fx-padding: 0px;");
        }

        // x-axis configs
        xAxis.setAutoRanging(false);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(points.size() + 1);

        // y-axis configs
        yAxis.setAutoRanging(false);
        yAxis.setMinorTickVisible(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);
        yAxis.setTickUnit(10); // Controls tick spacing

        // update chart data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().addAll(points);
        lineChart.setLegendVisible(false);
        lineChart.getData().setAll(series);
    }

    public void updatePerformanceLabel() {

        if (gameSessions.size() > 3) {
            StandardDeviationCalculator sdv = new StandardDeviationCalculator(points);
            performanceLabel.setText(
                    String.format("Your last game was better than %.1f%% of previous games.", sdv.getPercentile()));
        } else {
            performanceLabel.setText(String.format("You have not played enough games yet for this data to be visable"));
        }
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
