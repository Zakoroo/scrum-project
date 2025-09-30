/**
 * The {@code CardFetcher} interface defines methods for loading and retrieving
 * {@link Card} objects from a data source, such as a JSON file.
 * Implementations of this interface provide functionality to load cards,
 * filter them by difficulty, and access all loaded cards.
 *
 *
 * @author EcoLogic Studios
 */
package com.ecologicstudios.utils;
import java.util.List;


public interface CardFetcher {
    /**
     * Loads cards from the specified data source.
     *
     * @param path the path to the data source (e.g., JSON file)
     */
    public void loadCards(String path);

    /**
     * Retrieves a list of cards filtered by the specified difficulty.
     *
     * @param difficulty the difficulty level to filter cards by (e.g., "Easy", "Medium", "Hard")
     * @return a list of {@link Card} objects matching the difficulty
     */
    public List<Card> getCardsByDifficulty(String difficulty);

    /**
     * Returns all loaded cards.
     *
     * @return a list of all {@link Card} objects
     */
    public List<Card> getAllCards();
}

