package com.ecologicstudios.utils;

import java.util.List;

/**
 * Test utility class for validating FeedbackCalculator functionality.
 * <p>
 * This class serves as a simple test harness to verify that the FeedbackCalculator
 * properly generates feedback messages based on CO2 emission values and card difficulty.
 * It loads cards from the JSON file and tests the feedback generation with sample data.
 * 
 * @author Ecologic Studios
 */
public class test {
    /**
     * Main entry point for testing the FeedbackCalculator functionality.
     * <p>
     * This method performs the following test operations:
     * <ul>
     * <li>Loads "Hard" difficulty cards from the JSON file</li>
     * <li>Creates a FeedbackCalculator instance with 10 rounds</li>
     * <li>Tests feedback generation with a CO2 value of 60</li>
     * <li>Prints the generated feedback message to console</li>
     * </ul>
     * 
     * @param args command-line arguments (not used in this test)
     */
    public static void main(String[] args) {
        CardFetcher cardFetcher = new JsonCardFetcher("src/main/resources/json/cards.json");
        List<Card> cards = cardFetcher.getCardsByDifficulty("Hard");
        int co2 = 60;

        FeedbackCalculator calc = new FeedbackCalculator(cards, 10);
        // String message = calc.getFeedback(co2);

        System.out.println(calc.getFeedback(co2));
    }
}