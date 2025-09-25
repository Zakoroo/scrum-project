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

public final class CardJsonIO {
    private static final Type CARD_LIST = new TypeToken<List<Card>>() {}.getType();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private CardJsonIO() {}

    public static List<Card> load(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        try (Reader r = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            List<Card> list = GSON.fromJson(r, CARD_LIST);
            return (list != null) ? list : new ArrayList<>();
        }
    }

    public static void save(Path file, List<Card> cards) throws IOException {
        Path parent = file.getParent();
        if (parent != null) Files.createDirectories(parent);   // handle files with no parent
        try (Writer w = Files.newBufferedWriter(file, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
            GSON.toJson(cards, CARD_LIST, w);
        }
    }

    /** Adds or replaces a card by id, sorted by id. */
    public static void upsert(Path file, Card card) throws IOException {
        List<Card> cards = load(file);
        Map<Integer, Card> byId = new TreeMap<>();
        for (Card c : cards) byId.put(c.id, c);
        byId.put(card.id, card);
        save(file, new ArrayList<>(byId.values()));
    }
}
