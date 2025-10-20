package com.ecologicstudios.utils;

/**
 * Utility class for rescaling scores to a normalized performance value.
 * <p>
 * This class provides a static method to evaluate a score by mapping it
 * linearly between a given best and worst score. The resulting performance
 * value is expressed as a percentage.
 * </p>
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class ScoreRescaler {
    /**
     * Constructs a new ScoreRescaler. This class is not intended to be instantiated.
     */
    public ScoreRescaler() {
    }

    /**
     * Evaluates a score by rescaling it between the given best and worst scores.
     *
     * @param score      The score to be evaluated.
     * @param bestScore  The best possible score.
     * @param worstScore The worst possible score.
     * @return The normalized performance score as a percentage.
     * @throws IllegalArgumentException If any input value is not finite or if
     *                                  bestScore equals worstScore.
     */
    public static double evaluate(double score, double bestScore, double worstScore) {
        if (!(Double.isFinite(score) && Double.isFinite(bestScore) && Double.isFinite(worstScore))) {
            throw new IllegalArgumentException("All input values must be finite.");
        }

        double numerator = worstScore - score;
        double denominator = worstScore - bestScore;
        if (denominator == 0.0) {
            throw new IllegalArgumentException("Best and worst score must differ.");
        }
 
        double performance = numerator / denominator;
        if (performance < 0.0) return 0.0;
        if (performance > 100.0) return 100.0;
        return performance * 100;
    }
}
