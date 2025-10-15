package com.ecologicstudios.utils;

/**
 * Utility class for computing a normalized performance score for a game
 * session.
 * <p>
 * The normalization linearly maps a player's score between a given
 * {@code bestScore}
 * and {@code worstScore}. The resulting performance value is expressed as a
 * percentage,
 * where:
 * <ul>
 * <li>100 represents the best possible performance (equal to
 * {@code bestScore})</li>
 * <li>0 represents the worst possible performance (equal to
 * {@code worstScore})</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Example:</b>
 * </p>
 * 
 * <pre>
 * PerformanceCalculator calculator = new PerformanceCalculator();
 * double performance = calculator.calculatePerformance(75, 50, 100);
 * // performance = 50.0
 * </pre>
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class PerformanceCalculator {
    /**
     * Creates a new, stateless {@code PerformanceCalculator}.
     */
    public PerformanceCalculator() {
    }

    /**
     * Calculates the normalized performance score for a player session.
     * <p>
     * Formula:
     * 
     * <pre>
     * basePerformance = ((worstScore - score) / (worstScore - bestScore)) * 100
     * </pre>
     * <p>
     * This produces a percentage value where higher numbers indicate better
     * performance.
     * </p>
     *
     * <p>
     * <b>Preconditions (not enforced):</b>
     * </p>
     * <ul>
     * <li>{@code worstScore > bestScore}</li>
     * <li>{@code score} is within the range [{@code bestScore},
     * {@code worstScore}]</li>
     * </ul>
     *
     * <p>
     * Violating these conditions may yield undefined or out-of-range results.
     * </p>
     *
     * @param score      the player's achieved score for the session
     * @param bestScore  the best (lowest) achievable score
     * @param worstScore the worst (highest) achievable score
     * @return a normalized performance value between 0 and 100
     */
    public double calculatePerformance(double score, double bestScore, double worstScore) {
        double basePerformance = ((worstScore - score) / (worstScore - bestScore)) * 100;
        return basePerformance;
    }
}
