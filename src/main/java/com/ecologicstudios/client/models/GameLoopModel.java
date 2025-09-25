package com.ecologicstudios.client.models;

import com.ecologicstudios.utils.CardFetcher;
import com.ecologicstudios.utils.JsonCardFetcher;
import com.ecologicstudios.utils.Card;
import java.util.Collections;
import java.util.List;


public class GameLoopModel {
    final private static String path = "src/main/resources/cards.json";
    private int count = 0;
    private static GameLoopModel instance;
    private List<Card> cards;
    private Card currentCard;
    private int totalResult;

    public static GameLoopModel getInstance() {
        if (instance == null) {
            instance = new GameLoopModel();
        }
        return instance;
    }

    private GameLoopModel() {} 

    public void newGame() {
        //Reset counter
        count = 0;
        
        // fetch cards from given path (in resources)
        CardFetcher fetcher = new JsonCardFetcher(path);
        cards = fetcher.getAllCards();

        // shuffel cards
        Collections.shuffle(cards);
    }

    public int increment() {
        count++;
        return count;
    }

    public boolean gameEnded() {
        return count == 10;
    }

    public Card nextCard() { 
        if(!gameEnded() && !cards.isEmpty()) {
            increment();
            currentCard = cards.removeFirst();
            return currentCard;
        }
        return null;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int co2) {
        totalResult = co2;
    }
}
