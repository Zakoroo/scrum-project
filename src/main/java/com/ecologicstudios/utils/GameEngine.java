package com.ecologicstudios.utils;

public class GameEngine {

    public boolean isGameOver(GameState s, Deck deck) {
        return deck.isEmpty();
    }

    public void applyChoice(GameState state, Card card, int pick) {
        Choice choice = card.alternatives.get(pick);
        state.addCO2(choice.getco2());
    }
}
