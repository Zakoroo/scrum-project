package com.ecologicstudios.utils;

import java.util.List;

/**
 * Represents a single scenario card in the game.
 * <p>
 * Each Card contains an id, a scenario description, a difficulty label and a
 * list of possible alternatives (choices) associated with CO2 values. Gson
 * deserialization requires the public no-arg constructor and public fields.
 * </p>
 *
 * @author EcoLogic Studios
 */

public class Card {
    /**
     * Unique identifier for the card (from JSON).
     */
    public int id;

    /**
     * A readable scenario shown to the player.
     */
    public String scenario;

    /**
     * Difficulty label for filtering (e.g. "Easy", "Medium", "Hard").
     */
    public String difficulty;

    /**
     * List of alternatives for this card.
     */
    public List<Alternative> alternatives;

    public Card() {
    }

    public Card(int id, String scenario, String difficulty, List<Alternative> alternatives) {

        this.id = id;
        this.scenario = scenario;
        this.difficulty = difficulty;
        this.alternatives = alternatives;
    }

    /**
     * Returns the card id.
     *
     * @return the id of this card
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the scenario text.
     *
     * @return the scenario string
     */
    public String getScenario() {
        return this.scenario;
    }

    /**
     * Returns the difficulty label.
     *
     * @return the difficulty string
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    public List<Alternative> getAlternatives() {
        return this.alternatives;
    }
}