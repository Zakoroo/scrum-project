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

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import com.ecologicstudios.client.models.GameLoopModel;
import com.ecologicstudios.utils.ChartBuilder;
import com.ecologicstudios.utils.GameHistory;
import com.ecologicstudios.utils.GameSession;
import com.ecologicstudios.utils.PerformanceCalculator;
import com.ecologicstudios.utils.StandardDeviationCalculator;

/**
 * Controller for the statistics view in the application.
 * <p>
 * This class manages the display of game statistics, including a history of
 * game sessions, a performance chart, and a performance label. It interacts
 * with the GameLoopModel to retrieve data and updates the UI components
 * accordingly.
 */
public final class StatController extends BaseController {
    // ------------------------------------------------------------------------//
    // external resources
    // ------------------------------------------------------------------------//
    private final String historyItemPath = "/fxml/historyItem.fxml";

    // ------------------------------------------------------------------------//
    // main fields
    // ------------------------------------------------------------------------//
    /**
     * Reference to the game model for tracking game phases and logic.
     */
    private GameLoopModel gameLoopModel;

    /**
     * List of all game sessions stored in the history JSON object.
     */
    private List<GameSession> gameSessions;

    /**
     * List of all points displayed on the history chart.
     */
    private List<XYChart.Data<Number, Number>> points;

    // ------------------------------------------------------------------------//
    // fxml elements
    // ------------------------------------------------------------------------//
    @FXML
    private HBox root;

    @FXML
    private Button returnBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private VBox historyList;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private Label performanceLabel;

    // ------------------------------------------------------------------------//
    // constructors and initialization
    // ------------------------------------------------------------------------//
    /**
     * Initializes the statistics view by setting up the game history, chart, and
     * performance label.
     */
    public void initialize() {
        this.gameLoopModel = GameLoopModel.getInstance();
        this.gameSessions = gameLoopModel.getHistory().getAllSessions();

        if (!this.gameSessions.isEmpty()) {
            List<Point2D> pointList = new ChartBuilder(gameSessions, new PerformanceCalculator()).getChartData();
            this.points = pointList.stream().map(elem -> new XYChart.Data<Number,Number>(elem.getX(), elem.getY())).toList();
            updateChart();
            updateHistory();
        }

        updatePerformanceLabel();
        setRoot((Node) root);
    }

    // ------------------------------------------------------------------------//
    // event handlers
    // ------------------------------------------------------------------------//
    /**
     * Handles the return to main menu button event.
     * <p>
     * Navigates the user back to the main menu interface after viewing their
     * statistics and performance insight.
     * 
     * @param event the ActionEvent from clicking the return button
     */
    @FXML
    private void handleReturn(ActionEvent e) {
        sceneManager.switchScene("/fxml/main.fxml");
    }

    /**
     * Handles the reset button event.
     * <p>
     * Clears the game history and updates the statistics view to reflect the
     * cleared data.
     * 
     * @param event the ActionEvent from clicking the reset button
     */
    @FXML
    private void handleReset(ActionEvent e) {

        // Clear history
        GameHistory history = gameLoopModel.getHistory();
        history.clearHistory();

        // Update gameSession
        this.gameSessions = gameLoopModel.getHistory().getAllSessions();

        // Clear all UI components
        historyList.getChildren().clear();
        lineChart.getData().clear();

        // Update stat view
        if (!this.gameSessions.isEmpty()) {
            List<Point2D> pointList = new ChartBuilder(gameSessions, new PerformanceCalculator()).getChartData();
            this.points = pointList.stream().map(elem -> new XYChart.Data<Number,Number>(elem.getX(), elem.getY())).toList();
            updateChart();
            updateHistory();
        }
        updatePerformanceLabel();
    }

    // ------------------------------------------------------------------------//
    // update history list
    // ------------------------------------------------------------------------//
    /**
     * Updates the history list UI component with game session data.
     */
    public void updateHistory() {
        try {
            for (GameSession session : gameSessions) {
                addHistoryItem(session);
            }
        } catch (IOException e) {
            System.err.println("Some error unexpected occurred while loading history items: " + e.getMessage());
        }
    }

    /**
     * Adds a history item to the history list UI component.
     * 
     * @param session the game session to add to the history list
     * @throws IOException if an error occurs while loading the history item
     */
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

    // ------------------------------------------------------------------------//
    // update statistics
    // ------------------------------------------------------------------------//
    /**
     * Updates the line chart with performance data.
     */
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

    /**
     * Updates the performance label with a message based on the user's game
     * performance.
     */
    public void updatePerformanceLabel() {

        if (gameSessions.size() > 3) {
            StandardDeviationCalculator sdv = new StandardDeviationCalculator(points);
            performanceLabel.setText(
                    String.format("Your last game was better than %.1f%% of previous games.", sdv.getPercentile()));
        } else {
            performanceLabel.setText(String.format("You have not played enough games yet for this data to be visable"));
        }
    }
}
