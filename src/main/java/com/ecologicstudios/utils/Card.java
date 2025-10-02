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
     * List of possible choices for this card. Each {@code Choice} contains the
     * displayed text and associated CO2 value.
     */
    public List<Choice> alternatives;

    /**
     * Create a fully-initialized Card.
     *
     * @param id the card identifier
     * @param scenario the scenario text shown to the player
     * @param difficulty the difficulty level label
     * @param alternatives the list of choice alternatives for this card
     */
    public Card(int id, String scenario, String difficulty, List<Choice> alternatives) {
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
    public int getId(){
      return this.id;
    }

    /**
     * Returns the scenario text.
     *
     * @return the scenario string
     */
    public String getScenario(){
      return this.scenario;
    }

    /**
     * Returns the difficulty label.
     *
     * @return the difficulty string
     */
    public String getDifficulty(){
      return this.difficulty;
    }

    /**
     * Returns the list of alternatives (choices) for this card.
     *
     * @return list of {@link Choice} objects (may be null)
     */
    public List<Choice> getAlternatives(){
      return this.alternatives;
    }

}