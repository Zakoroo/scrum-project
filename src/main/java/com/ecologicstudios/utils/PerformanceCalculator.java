package com.ecologicstudios.utils;

import java.util.List;

/**
 * Computes a normalized performance score for a game session based on the
 * player's raw score relative to the session's best and worst scores, then
 * applies a difficulty-dependent weight.
 * <p>
 * The normalization maps scores linearly between {@code bestScore} and
 * {@code worstScore}, then multiplies by a weight derived from {@code difficulty}.
 * For typical inputs where {@code bestScore <= score <= worstScore}, the
 * unweighted value is in {@code [0, 1]}. With weighting, the result is in
 * {@code [0, 2]} for the predefined difficulties.
 * </p>
 */
public class PerformanceCalculator {

    /**
     * Creates a new, stateless PerformanceCalculator.
     */
    public PerformanceCalculator(){}

    /**
     * Calculates the performance value.
     * <p>
     * Formula:
     * <pre>
     * basePerformance = (worstScore - score) / (worstScore - bestScore)
     * weight = 1.0 (easy), 1.5 (medium), 2.0 (hard), otherwise 1.0
     * result = basePerformance * weight
     * </pre>
     * The {@code difficulty} string is trimmed and compared case-insensitively.
     * If it does not match a known level, a default weight of {@code 1.0} is used
     * and a message is printed to standard out.
     * </p>
     * <p>
     * Preconditions (not enforced):
     * <ul>
     *   <li>{@code worstScore > bestScore}</li>
     *   <li>{@code difficulty != null}</li>
     *   <li>{@code score} is expected to be within [{@code bestScore}, {@code worstScore}]</li>
     * </ul>
     * Violating these may yield undefined or out-of-range results.
     * </p>
     *
     * @param score       the player's total score for the session
     * @param bestScore   the best (lowest) achievable score for the session
     * @param worstScore  the worst (highest) achievable score for the session
     * @param difficulty  the session difficulty; recognized values are "easy", "medium", and "hard"
     * @return the weighted performance score
     */
    public double calculatePerformance(double score, double bestScore, double worstScore, String difficulty) {

        double basePerformance = (worstScore - score) / (worstScore - bestScore);

        double weight;
            switch (difficulty.trim().toLowerCase()) {
                case "easy":
                    weight = 1.0;
                    break;
                case "medium": 
                    weight = 1.5;
                    break;
                case "hard":
                    weight = 2.0;
                    break;
                default:
                    System.out.println("No weight for " + difficulty + ", using default weight of 1.0");
                    weight = 1.0;
                    break;
            }
        return basePerformance * weight;
    }
}