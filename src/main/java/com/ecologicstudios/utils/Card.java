/**
 * Represents a single scenario card in the game.
 * <p>
 * Each Card contains an id, a scenario description, a difficulty label and a
 * list of possible alternatives (choices) associated with CO2 values.
 * Gson deserialization requires the public no-arg constructor and public fields.
 * </p>
 *
 * @author EcoLogic Studios
 */
package com.ecologicstudios.utils;

import java.util.List;

/**
 * Represents a single scenario card in the environmental impact game.
 * <p>
 * Each Card contains a unique identifier, a scenario description, a difficulty level,
 * and a list of possible alternatives (choices) with associated CO2 emission values.
 * Cards are used to present environmental scenarios to players and collect their
 * choices for impact calculation.
 * <p>
 * This class is designed for JSON serialization/deserialization using Gson,
 * which requires public fields and a no-argument constructor.
 *
 * @author Ecologic Studios
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

    /**
     * Default no-argument constructor required for Gson deserialization.
     */
    public Card() {
    }

    /**
     * Constructs a new Card with the specified parameters.
     *
     * @param id          the unique identifier for this card
     * @param scenario    the environmental scenario description to present to the player
     * @param difficulty  the difficulty level (e.g., "Easy", "Medium", "Hard")
     * @param alternatives the list of alternative choices available for this scenario
     */
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

    /**
     * Returns the list of alternative choices for this card.
     * <p>
     * Each alternative represents a possible choice the player can make
     * in response to the environmental scenario, with associated CO2 impact values.
     *
     * @return the list of alternatives available for this card
     */
    public List<Alternative> getAlternatives() {
        return this.alternatives;
    }
}