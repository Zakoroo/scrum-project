package com.ecologicstudios.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.XYChart;

/**
 * Builds JavaFX chart data points from a list of game sessions using a
 * {@link PerformanceCalculator}.
 * <p>
 * For each {@link GameSession}, the X value is the session id and the Y value
 * is the computed performance score.
 * </p>
 *
 * @see GameSession
 * @see PerformanceCalculator
 * @see javafx.scene.chart.XYChart.Data
 */
public class ChartBuilder {
    /**
     * The list of game sessions to visualize, processed in the current order.
     */
    List<GameSession> history;
    /**
     * Strategy used to compute a normalized performance value per session.
     */    
    PerformanceCalculator performanceCalculator;
    /**
     * Creates a ChartBuilder.
     *
     * @param history the list of game sessions to convert into chart points; must not be null
     * @param performanceCalculator the calculator used to compute Y-values; must not be null
     */    
    public ChartBuilder(List<GameSession> history, PerformanceCalculator performanceCalculator){
        this.history = history;
        this.performanceCalculator = performanceCalculator;
    }
    /**
     * Produces chart data points for a JavaFX {@link XYChart} where:
     * <ul>
     *   <li>X = {@code sessionId}</li>
     *   <li>Y = {@code performanceCalculator.calculatePerformance(totalScore, bestScore, worstScore, difficulty)}</li>
     * </ul>
     *
     * @return a new list of {@link XYChart.Data} entries, in the same order as {@link #history}
     */
    public List<XYChart.Data<Number, Number>> getChartData(){
        List<XYChart.Data<Number, Number>> data = new ArrayList<>();
        
        for(GameSession session : history){
            
            int id = session.getSessionId();
            double score = session.getTotalScore();
            double worst = session.getWorstScore();
            double best = session.getBestScore();

            int x = id;
            double y = performanceCalculator.calculatePerformance(score, best, worst);
            
            data.add(new XYChart.Data<>(x, y));
        }
        return data;
    }
}