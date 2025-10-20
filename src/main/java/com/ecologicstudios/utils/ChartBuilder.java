package com.ecologicstudios.utils;

import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

import javafx.scene.chart.XYChart;

/**
 * Builds JavaFX chart data points from a list of game sessions using a
 * {@link ScoreRescaler}.
 * <p>
 * For each {@link GameSession}, the X value is the session id and the Y value
 * is the computed performance score.
 * </p>
 *
 * @see GameSession
 * @see ScoreRescaler
 * @see javafx.scene.chart.XYChart.Data
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class ChartBuilder {
    /**
     * The list of game sessions to visualize, processed in the current order.
     */
    private List<GameSession> history;

    /**
     * Creates a ChartBuilder.
     *
     * @param history               the list of game sessions to convert into chart
     *                              points; must not be null
     * @param performanceCalculator the calculator used to compute Y-values; must
     *                              not be null
     */
    public ChartBuilder(List<GameSession> history) {
        this.history = history;
    }

    /**
     * Produces chart data points for a JavaFX {@link XYChart} where:
     * <ul>
     * <li>X = {@code sessionId}</li>
     * <li>Y =
     * {@code performanceCalculator.calculatePerformance(totalScore, bestScore, worstScore, difficulty)}</li>
     * </ul>
     *
     * @return a new list of {@link XYChart.Data} entries, in the same order as
     *         {@link #history}
     */
    public List<Point2D> getChartData() {
        List<Point2D> data = new ArrayList<>();

        for (GameSession session : history) {

            int id = session.getSessionId();
            double score = session.getTotalScore();
            double worst = session.getWorstScore();
            double best = session.getBestScore();

            double x = id;
            double y = ScoreRescaler.evaluate(score, best, worst);

            data.add(new Point2D.Double(x, y));
        }
        return data;
    }
}