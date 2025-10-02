/**
 * The {@code JsonCardFetcher} class is responsible for loading card data from a JSON file.
 * It implements the {@link CardFetcher} interface and provides methods to retrieve cards
 * based on difficulty level.
 *
 * <p>
 * This class reads a JSON file containing card information, parses the data using Gson,
 * and constructs {@link Card} objects with their associated choices. It can filter cards
 * by difficulty and provide access to all loaded cards.
 * </p>
 *
 * @author EcoLogic Studios
 */

package com.ecologicstudios.utils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


public class JsonCardFetcher implements CardFetcher{
        /**
         * Wrapper for all loaded cards.
         */
        public CardWrapper allCards;

        /**
         * Constructs a JsonCardFetcher with the specified path to the JSON file.
         *
         * @param path the path to the JSON file containing card data
         */
        public JsonCardFetcher(String path){
                loadCards(path);
        }

        /**
         * Reads the contents of a JSON file and returns it as a String.
         *
         * @param path the path to the JSON file
         * @return the contents of the file as a String, or an empty string if an error occurs
         */
        public String JsonToString(String path){
                StringBuilder stringbuilder = new StringBuilder();
                try (FileReader filereader = new FileReader(path)) {
                        int i;
                        while((i = filereader.read()) != -1) {
                        stringbuilder.append((char) i);
                        }
                        filereader.close();
                        return stringbuilder.toString();
                        } 
                catch (IOException e) {
                        e.printStackTrace();
                        return "";
                }
        }

        /**
         * Loads cards from the specified JSON file and initializes the {@code allCards} field.
         *
         * @param path the path to the JSON file containing card data
         */
        public void loadCards(String path){
                Gson gson = new Gson();
                allCards = gson.fromJson(JsonToString(path), CardWrapper.class);
        }

        /**
         * Retrieves a list of cards filtered by the specified difficulty.
         *
         * @param difficulty the difficulty level to filter cards by (e.g., "Easy", "Medium", "Hard")
         * @return a list of {@link Card} objects matching the difficulty
         */
        public List<Card> getCardsByDifficulty(String difficulty){

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
        public List<Card> getAllCards(){
                return allCards.getCardList();
        }
}