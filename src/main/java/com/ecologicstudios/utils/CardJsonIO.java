package com.ecologicstudios.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Utility class for JSON input/output operations on Card collections.
 * <p>
 * This class provides static methods for loading, saving, and updating card data
 * in JSON format. It uses Gson for serialization/deserialization and handles
 * file operations with proper error handling and UTF-8 encoding.
 * <p>
 * All methods are static and the class cannot be instantiated.
 *
 * @author Ecologic Studios
 * @version 1.0
 */
public final class CardJsonIO {
    /**
     * Type token for Gson to handle List&lt;Card&gt; serialization/deserialization.
     */
    private static final Type CARD_LIST = new TypeToken<List<Card>>() {}.getType();

    /**
     * Gson instance configured with pretty printing for readable JSON output.
     */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CardJsonIO() {}

    /**
     * Loads a list of cards from a JSON file.
     * <p>
     * If the file doesn't exist, returns an empty list. If the file exists but
     * contains invalid JSON or null content, returns an empty list.
     *
     * @param file the path to the JSON file to load
     * @return a list of cards loaded from the file, or an empty list if file doesn't exist
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static List<Card> load(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        try (Reader r = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            List<Card> list = GSON.fromJson(r, CARD_LIST);
            return (list != null) ? list : new ArrayList<>();
        }
    }

    /**
     * Saves a list of cards to a JSON file with pretty formatting.
     * <p>
     * Creates parent directories if they don't exist. The file is created or
     * truncated if it already exists, and written with UTF-8 encoding.
     *
     * @param file the path where the JSON file should be saved
     * @param cards the list of cards to save
     * @throws IOException if an I/O error occurs while writing the file
     */
    public static void save(Path file, List<Card> cards) throws IOException {
        Path parent = file.getParent();
        if (parent != null) Files.createDirectories(parent);   // handle files with no parent
        try (Writer w = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
            GSON.toJson(cards, CARD_LIST, w);
        }
    }

    /**
     * Adds a new card or replaces an existing card with the same ID.
     * <p>
     * This method loads the existing cards, updates or adds the specified card
     * based on its ID, and saves the result back to the file. The cards are
     * automatically sorted by ID in the output file.
     *
     * @param file the path to the JSON file to update
     * @param card the card to add or update
     * @throws IOException if an I/O error occurs while reading or writing the file
     */
    public static void upsert(Path file, Card card) throws IOException {
        List<Card> cards = load(file);
        Map<Integer, Card> byId = new TreeMap<>();
        for (Card c : cards) byId.put(c.id, c);
        byId.put(card.id, card);
        save(file, new ArrayList<>(byId.values()));
    }
}
