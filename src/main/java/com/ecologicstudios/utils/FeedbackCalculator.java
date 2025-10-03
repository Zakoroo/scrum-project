package com.ecologicstudios.utils;

import java.util.List;

/**
 * The {@code FeedbackCalculator} class provides methods to calculate feedback
 * based on the player's choices in a card-based game. It can compute the
 * minimum, maximum, and average possible CO2 results for a given set of cards
 * and round length, and generate feedback messages according to the player's
 * performance.
 *
 * @author EcoLogic Studios
 */
public class FeedbackCalculator {
    /**
     * The list of cards to be evaluated for feedback and CO2 calculations.
     */
    List<Card> cards;

    /**
     * The number of rounds (cards) to consider in the calculations.
     */
    int roundLength;

    /**
     * Constructs a FeedbackCalculator with the specified list of cards and round
     * length.
     *
     * @param cards       the list of cards to evaluate
     * @param roundLength the number of rounds to consider
     */
    public FeedbackCalculator(List<Card> cards, int roundLength) {
        this.cards = cards;
        this.roundLength = roundLength;
    }

    /**
     * Calculates the minimum possible total CO2 result by selecting the lowest CO2
     * choice for each card.
     *
     * @param cards       the list of cards to evaluate
     * @param roundLength the number of cards to consider (e.g., round length)
     * @return the sum of the minimum CO2 values for each card
     */
    public double getMinResult(List<Card> cards, int roundLength) {
        double minTotal = 0;
        for (int i = 0; i < roundLength && i < cards.size(); i++) {
            List<Alternative> alternatives = cards.get(i).getAlternatives();
            double minCo2 = Double.MAX_VALUE;
            for (Alternative alt : alternatives) {
                if (alt.getco2() < minCo2) {
                    minCo2 = alt.getco2();
                }
            }
            minTotal += minCo2;
        }

        return minTotal;
    }

    /**
     * Calculates the maximum possible total CO2 result by selecting the highest CO2
     * choice for each card.
     *
     * @param cards       the list of cards to evaluate
     * @param roundLength the number of cards to consider (e.g., round length)
     * @return the sum of the maximum CO2 values for each card
     */
    public double getMaxResult(List<Card> cards, int roundLength) {
        double maxTotal = 0;
        for (int i = 0; i < roundLength && i < cards.size(); i++) {
            List<Alternative> choices = cards.get(i).getAlternatives();
            double maxCo2 = Double.MIN_VALUE;
            for (Alternative choice : choices) {
                if (choice.getco2() > maxCo2) {
                    maxCo2 = choice.getco2();
                }
            }
            maxTotal += maxCo2;
        }

        return maxTotal;
    }

    /**
     * Calculates the average result between two values.
     *
     * @param a the first value
     * @param b the second value
     * @return the average of a and b
     */
    public double getAverageResult(double a, double b) {
        return (a + b) / 2;
    }

    /**
     * Evaluates feedback based on the player's score compared to the minimum,
     * maximum, and average results.
     *
     * @param min   the minimum possible result
     * @param max   the maximum possible result
     * @param avg   the average result
     * @param point the player's score
     * @return a feedback message as a String
     */
    public String feedbackEvaluator(double min, double max, double avg, double point) {
        System.out.println(min);
        System.out.println(max);
        System.out.println(avg);

        if (point >= min && point < avg / 2) {
            return "You're a climate hero! Your choices show deep environmental care.";
        } else if (point >= avg / 2 && point < avg) {
            return "You're making mindful decisions! Your eco-awareness is inspiring.";
        } else if (point >= avg && point < (3 * avg) / 2) {
            return "Your lifestyle is unsustainable. You're actively making the climate crisis worse!";
        } else if (point >= (3 * avg) / 2 && point <= max) {
            return "This is climate negligence. Your choices show complete disregard for the planet's future!";
        } else {
            return "error";
        }
    }

    /**
     * Generates feedback for the player's score based on the cards and round
     * length.
     *
     * @param point the player's score
     * @return a feedback message as a String
     */
    public String getFeedback(double point) {
        double min = getMinResult(cards, roundLength);
        double max = getMaxResult(cards, roundLength);
        double avg = getAverageResult(min, max);
        return feedbackEvaluator(min, max, avg, point);
    }
}