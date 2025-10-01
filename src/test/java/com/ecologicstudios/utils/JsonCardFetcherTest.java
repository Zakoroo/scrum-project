package com.ecologicstudios.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonCardFetcherTest {

    @TempDir
    Path tempDir;

    private String testJsonPath;

    @BeforeEach
    void setUp() throws IOException {
        // JSON aligned with your Card (id, scenario, difficulty, alternatives[choice, co2])
        String jsonContent = """
        { "cards": [
          {"id":1,"scenario":"Q1","difficulty":"easy","alternatives":[{"choice":"A1","co2":0}]},
          {"id":2,"scenario":"Q2","difficulty":"hard","alternatives":[{"choice":"A2","co2":0}]}
        ] }
        """;
        Path file = tempDir.resolve("test_cards.json");
        Files.writeString(file, jsonContent);
        testJsonPath = file.toString();
    }

    @Test
    void testJsonToStringReadsFile() {
        JsonCardFetcher fetcher = new JsonCardFetcher(testJsonPath);
        String json = fetcher.JsonToString(testJsonPath);
        assertTrue(json.contains("\"difficulty\":\"easy\""));
        assertTrue(json.contains("\"difficulty\":\"hard\""));
    }

    @Test
    void testLoadCardsParsesCards() {
        JsonCardFetcher fetcher = new JsonCardFetcher(testJsonPath);
        // use public API, not internal fields
        List<Card> all = fetcher.getAllCards();
        assertEquals(2, all.size());
        assertEquals("Q1", all.get(0).getScenario());
        assertEquals("Q2", all.get(1).getScenario());
    }

    @Test
    void testGetCardsByDifficulty() {
        JsonCardFetcher fetcher = new JsonCardFetcher(testJsonPath);
        assertEquals("Q1", fetcher.getCards("easy").get(0).getScenario());
        assertEquals("Q2", fetcher.getCards("hard").get(0).getScenario());

        // case-insensitive check (your code uses equalsIgnoreCase)
        assertEquals("Q1", fetcher.getCards("EASY").get(0).getScenario());

        // unknown difficulty -> empty list
        assertTrue(fetcher.getCards("medium").isEmpty());
    }

    @Test
    void testGetAllCardsReturnsAll() {
        JsonCardFetcher fetcher = new JsonCardFetcher(testJsonPath);
        assertEquals(2, fetcher.getAllCards().size());
    }
}
