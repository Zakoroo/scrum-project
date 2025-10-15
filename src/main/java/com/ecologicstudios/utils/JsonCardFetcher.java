package com.ecologicstudios.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 * The {@code JsonCardFetcher} class is responsible for loading card data from a
 * JSON file. It implements the {@link CardFetcher} interface and provides
 * methods to retrieve cards based on difficulty level.
 *
 * <p>
 * This class reads a JSON file containing card information, parses the data
 * using Gson, and constructs {@link Card} objects with their associated
 * choices. It can filter cards by difficulty and provide access to all loaded
 * cards.
 * </p>
 *
 * @author Ecologic Studios
 * @version 1.0
 */
public class JsonCardFetcher implements CardFetcher {
    /**
     * Wrapper for all loaded cards.
     */
    public CardWrapper allCards;

    /**
     * Constructs a new JsonCardFetcher and loads cards from the specified file
     * path.
     * 
     * @param path the file path to the JSON file containing card data
     */
    public JsonCardFetcher(String path) {
        loadCards(path);
    }

    /**
     * Reads a JSON file and converts its contents to a string.
     * 
     * @param path the file path to the JSON file to read
     * @return the contents of the JSON file as a string, or empty string if an
     *         error occurs
     */
    public String JsonToString(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "";
        }
        return stringBuilder.toString();
    }

    /**
     * Loads cards from the specified JSON file and initializes the {@code allCards}
     * field.
     *
     * @param path the path to the JSON file containing card data
     */
    public void loadCards(String path) {
        Gson gson = new Gson();
        allCards = gson.fromJson(JsonToString(path), CardWrapper.class);
    }

    /**
     * Retrieves a list of cards filtered by the specified difficulty.
     *
     * @param difficulty the difficulty level to filter cards by (e.g., "Easy",
     *                   "Medium", "Hard")
     * @return a list of {@link Card} objects matching the difficulty
     */
    public List<Card> getCardsByDifficulty(String difficulty) {
        List<Card> CardList = new ArrayList<>();
        for (Card c : allCards.getCardList()) {
            if (c.difficulty.equalsIgnoreCase(difficulty)) {
                CardList.add(c);
            }
        }
        return CardList;
    }

    /**
     * Returns all loaded cards.
     *
     * @return a list of all {@link Card} objects
     */
    public List<Card> getAllCards() {
        return allCards.getCardList();
    }
}