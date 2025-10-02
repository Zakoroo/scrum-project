package com.ecologicstudios.utils;

import java.util.List;

public class test {
    public static void main(String[] args) {
        CardFetcher cardfetcher = new JsonCardFetcher("src/main/resources/cards.json");
        List<Card> cards = cardfetcher.getCardsByDifficulty("Hard");
        int co2 = 60;

        FeedbackCalculator calc = new FeedbackCalculator(cards, 10);
        // String message = calc.getFeedback(co2);

        System.out.println(calc.getFeedback(co2));
    }
}