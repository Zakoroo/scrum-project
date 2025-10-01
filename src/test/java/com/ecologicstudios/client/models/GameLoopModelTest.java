package com.ecologicstudios.client.models;

import com.ecologicstudios.utils.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameLoopModelTest {

    private GameLoopModel model;

    // Helper to inject cards
    private void injectCards(List<Card> cards) throws Exception {
        var field = GameLoopModel.class.getDeclaredField("cards");
        field.setAccessible(true);
        field.set(model, cards);
    }

    @BeforeEach
    public void setUp() {
        // Reset singleton for isolation
        try {
            var instanceField = GameLoopModel.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model = GameLoopModel.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        GameLoopModel m1 = GameLoopModel.getInstance();
        GameLoopModel m2 = GameLoopModel.getInstance();
        assertSame(m1, m2);
    }

    @Test
    public void testNewGameResetsCountAndFetchesCards() throws Exception {
        model.newGame();
        assertEquals(0, getPrivateCount());
        assertNotNull(getPrivateCards());
        assertFalse(getPrivateCards().isEmpty());
    }

    @Test
    public void testIncrement() {
        assertEquals(1, model.increment());
        assertEquals(2, model.increment());
    }

    @Test
    public void testGameEnded() throws Exception {
        injectCards(new ArrayList<>());
        for (int i = 0; i < 9; i++) {
            model.increment();
            assertFalse(model.gameEnded());
        }
        model.increment();
        assertTrue(model.gameEnded());
    }

    @Test
    public void testNextCardReturnsCardAndIncrements() throws Exception {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(0, "Card 1", "Description 1", null);
        Card card2 = new Card(1, "Card 2", "Description 2", null);
        cards.add(card1);
        cards.add(card2);
        injectCards(cards);

        assertEquals(card1, model.nextCard());
        assertEquals(1, getPrivateCount());
        assertEquals(card2, model.nextCard());
        assertEquals(2, getPrivateCount());
        assertNull(model.nextCard());
    }

    @Test
    public void testNextCardReturnsNullIfGameEndedOrNoCards() throws Exception {
        injectCards(new ArrayList<>());
        for (int i = 0; i < 10; i++) model.increment();
        assertNull(model.nextCard());
    }

    @Test
    public void testTotalResultGetterSetter() {
        model.setTotalResult(42);
        assertEquals(42, model.getTotalResult());
    }

    // Helpers to access private fields
    private int getPrivateCount() {
        try {
            var field = GameLoopModel.class.getDeclaredField("count");
            field.setAccessible(true);
            return (int) field.get(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Card> getPrivateCards() {
        try {
            var field = GameLoopModel.class.getDeclaredField("cards");
            field.setAccessible(true);
            return (List<Card>) field.get(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}