package com.ecologicstudios.utils;

public class ScoreRescaler {
    public ScoreRescaler() {
    }
    
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
