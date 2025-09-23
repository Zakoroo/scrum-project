package com.ecologicstudios.utils;

import java.util.Collection;
import java.util.*;

public class Deck {
    private final Deque<Card> cards;

    public Deck(List<Card> cards) {
        List<Card> shuffled = new ArrayList<>(cards);
        Collections.shuffle(shuffled, new Random());
        this.cards = new ArrayDeque<>(shuffled);
    }

    public Card drawCard() {
        return cards.pollFirst();
    }
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getAllCards() {
        return new ArrayList<>(cards);
    }

    public void showDeck() {
        if (cards.isEmpty()) {
            System.out.println("The deck is empty!");
            return;
        }

        System.out.println("Deck contains " + cards.size() + " cards:");
        System.out.println("------------------------------------");

        int index = 1;
        for (Card card : cards) {
            System.out.println("Card #" + index + " (ID: " + card.id + ")");
            System.out.println("Scenario: " + card.scenario);
            System.out.println("Difficulty: " + card.difficulty);
            System.out.println("Choices:");
            for (Choice choice : card.alternatives) {
                System.out.println("   - " + choice.getChoice() + " (CO₂: " + choice.getco2() + ")");
            }
            System.out.println("------------------------------------");
            index++;
        }
    }

}