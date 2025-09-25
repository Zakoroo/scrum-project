package com.ecologicstudios.utils;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {


    public static void main (String[] args) {
        System.out.println("Welcome to the EcoLogic Studios Console Application!");
        CardFetcher cardfetcher = new JsonCardFetcher();
        Cards testCards = new Cards(cardfetcher.getAllCards());
        Deck deck = new Deck(testCards.getCardList());
        GameEngine engine = new GameEngine();
        GameState state = new GameState();
        
        try (Scanner sc = new Scanner(System.in)) {
            while (!engine.isGameOver(state, deck)) {
                Card card = deck.drawCard();
                if (card == null) break;

                System.out.println("\n== " + card.difficulty + " ==");
                System.out.println(card.scenario);
                for (int i = 0; i < card.alternatives.size(); i++) {
                    System.out.printf("  [%d] %s%n", i + 1, card.alternatives.get(i).choice);
                }
                System.out.print("> ");
                int pick = Integer.parseInt(sc.nextLine()) - 1;

                engine.applyChoice(state, card, pick);
                System.out.println("Current CO2: " + state.getTotalCO2());
            }
            System.out.println("\nGame over! Total CO2: " + state.getTotalCO2());
        }
    }

}
