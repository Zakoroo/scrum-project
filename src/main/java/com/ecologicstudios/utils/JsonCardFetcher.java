package com.ecologicstudios.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 * Implementation of CardFetcher that loads cards from a JSON file.
 * <p>
 * This class uses Gson to parse JSON data containing card information
 * and provides methods to retrieve cards based on difficulty level
 * or get all available cards.
 */
public class JsonCardFetcher implements CardFetcher {
    /**
     * Container object that holds all cards loaded from the JSON file.
     */
    public Cards allCards;

    /**
     * Constructs a new JsonCardFetcher and loads cards from the specified file path.
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
     * @return the contents of the JSON file as a string, or empty string if an error occurs
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
     * Loads cards from a JSON file using Gson deserialization.
     * <p>
     * This method reads the JSON file, converts it to a string, and then
     * deserializes it into a Cards object using Gson.
     * 
     * @param path the file path to the JSON file containing card data
     */
    public void loadCards(String path) {
        Gson gson = new Gson();
        allCards = gson.fromJson(JsonToString(path), Cards.class);
    }

    /**
     * Retrieves a list of cards filtered by the specified difficulty level.
     * <p>
     * The comparison is case-insensitive, so "easy", "Easy", and "EASY" 
     * will all match cards with difficulty "Easy".
     * 
     * @param difficulty the difficulty level to filter by (case-insensitive)
     * @return a list of cards matching the specified difficulty level
     */
    public List<Card> getCards(String difficulty) {
        List<Card> cardList = new ArrayList<>();
        for (Card c : allCards.getCardList()) {
            if (c.difficulty.equalsIgnoreCase(difficulty)) {
                cardList.add(c);
            }
        }
        return cardList;
    }

    /**
     * Retrieves all cards regardless of difficulty level.
     * 
     * @return a list containing all loaded cards
     */
    public List<Card> getAllCards() {
        return allCards.getCardList();
    }
}