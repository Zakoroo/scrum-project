package com.ecologicstudios.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChoiceTest {

    @Test
    void testConstructorAndGetters() {
        Choice choice = new Choice("Recycle", 10);
        assertEquals("Recycle", choice.getChoice());
        assertEquals(10, choice.getco2());
    }

    @Test
    void testNegativeCo2Value() {
        Choice choice = new Choice("Drive", -5);
        assertEquals("Drive", choice.getChoice());
        assertEquals(-5, choice.getco2());
    }

    @Test
    void testEmptyChoiceText() {
        Choice choice = new Choice("", 0);
        assertEquals("", choice.getChoice());
        assertEquals(0, choice.getco2());
    }
}
