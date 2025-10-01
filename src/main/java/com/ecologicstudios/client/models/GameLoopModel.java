package com.ecologicstudios.client.models;

import java.util.Collections;
import java.util.List;

import com.ecologicstudios.utils.CardFetcher;
import com.ecologicstudios.utils.JsonCardFetcher;
import com.ecologicstudios.utils.Card;
import com.ecologicstudios.utils.Alternative;

/**
 * Singleton model class that manages the game state and logic for the CO2 emission in the game.
 * <p>
 * This class handles the game flow including card management, answer processing,
 * score calculation, and game session configuration. It implements the singleton
 * pattern to ensure only one game instance exists at a time.
 * <p>
 * The game consists of presenting cards with environmental scenarios and alternatives
 * to the player, tracking their choices and calculating the total CO2 emissions
 * based on their decisions.
 * 
 * @author EcoLogic Studios
 */
public class GameLoopModel {
    /**
     * Path to the JSON file that store the cards.
     */
    final private static String path = "src/main/resources/cards.json";

    /**
     * The singleton instance of this class.
     */
    private static GameLoopModel instance;

    /**
     * List of cards for the current game session.
     */
    private List<Card> cards;

    /**
     * Highest card in the card deck.
     */
    private Card currentCard;

    /**
     * The difficulty of the current session ('Easy' by default)
     */
    private String difficulty = "Easy";

    /**
     * The maximum number of rounds for the current game session (default is 10).
     */
    private int maxNumCards = 10;

    /**
     * Counts of the number of rounds during the current game session.
     */
    private int cardsCount = 0;

    /**
     * Count of the number of cards that has been answered so far.
     */
    private int answersCount = 0;

    /**
     * The total result for the current game session representing the total emission
     * due to players answers.
     */
    private int totalResult = 0;

    /**
     * This method replaces the functionality of the constructor effectively
     * disabling creating more than one instance.
     * 
     * @return returns the singleton instance of this class.
     */
    public static GameLoopModel getInstance() {
        if (instance == null) {
            instance = new GameLoopModel();
        }
        return instance;
    }

    /**
     * The default constructor is disabled to prevent creating more instances of
     * this class.
     */
    private GameLoopModel() {
    }

    /**
     * Resets the game fields to their initial value and prepares a new game
     * session. This method initializes a new card deck based on the current
     * difficulty setting, shuffles the cards, and resets the round counter.
     */
    public void newGame() {
        cardsCount = 0; // reset the counter
        answersCount = 0;
        totalResult = 0;
        CardFetcher fetcher = new JsonCardFetcher(path); // fetch cards from given path (in resources)
        cards = fetcher.getCards(difficulty); // fetch out only cards of the given difficulty
        Collections.shuffle(cards); // shuffle cards
    }

    /**
     * Checks if the current game session has ended.
     * 
     * @return true if the number of rounds played equals the maximum number of
     *         rounds, false otherwise
     */
    public boolean gameEnded() {
        return cardsCount == maxNumCards && answersCount == maxNumCards;
    }

    /**
     * Retrieves the next card from the deck and advances the game by one round.
     * <p>
     * This method will only return a card if the game hasn't ended, there are
     * cards available, and the current card has been answered (cardsCount equals answersCount).
     * 
     * @return the next Card from the deck
     * @throws IllegalStateException if the game has ended, no cards are available,
     *                              or the current card hasn't been answered yet
     */
    public Card nextCard() throws IllegalStateException {
        if (!gameEnded() && !cards.isEmpty() && cardsCount == answersCount) {
            cardsCount++;
            currentCard = cards.removeFirst();
            return currentCard;
        }
        throw new IllegalStateException(cards.isEmpty() ? "no enough cards" : "game already ended");
    }

    /**
     * Submits an answer for the current card and updates the total result.
     * <p>
     * This method checks if the provided {@code answer} exists among the
     * alternatives of the current card. If the answer is valid, its CO2 value is
     * added to the total result. If the answer is not found, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param answer the selected {@link Alternative} to submit
     * @throws IllegalArgumentException if the provided answer is not a valid
     *                                  alternative for the current card
     */
    public void submitAnswer(Alternative answer) throws IllegalArgumentException {
        for (Alternative alt : currentCard.getAlternatives()) {
            if (alt == answer) {
                answersCount++;
                totalResult += answer.getco2();
                return;
            }
        }
        throw new IllegalArgumentException("answer invalid");
    }

    /**
     * Gets the total CO2 result for the current game session.
     * <p>
     * This method can only be called after the game has ended. Attempting to
     * access the result before game completion will result in an exception.
     * 
     * @return the total CO2 result accumulated during the current game
     * @throws IllegalAccessError if the game hasn't ended yet
     */
    public int getTotalResult() throws IllegalAccessError {
        if (!gameEnded()) {
            throw new IllegalAccessError("cannot get result before game ends");
        }
        return totalResult;
    }

    /**
     * Sets the maximum number of cards for the game session.
     * <p>
     * This setting determines how many cards will be presented to the player
     * during a single game session.
     * 
     * @param numRounds the maximum number of cards to be played in the current session
     */
    public void setMaxNumCards(int numRounds) {
        this.maxNumCards = numRounds;
    }

    /**
     * Sets the difficulty level for the current game session.
     * 
     * @param difficulty the difficulty level (e.g., "Easy", "Medium", "Hard")
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the current difficulty level of the game session.
     * 
     * @return the current difficulty level as a String
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     * Gets the maximum number of rounds configured for the current game session.
     * 
     * @return the maximum number of rounds
     */
    public int getMaxNumCards() {
        return this.maxNumCards;
    }
}
