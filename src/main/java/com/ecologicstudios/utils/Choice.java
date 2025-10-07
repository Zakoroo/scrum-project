/**
 * The {@code Choice} class represents an alternative option for a card scenario,
 * including its descriptive text and associated CO2 value.
 * <p>
 * Each {@code Choice} object contains the choice description and the CO2 impact
 * of selecting that choice.
 *
 * @author EcoLogic Studios
 */
package com.ecologicstudios.utils;

public class Choice{
    /**
     * The description of the choice presented to the player.
     */
    String choice;
    /**
     * The CO2 value associated with this choice.
     */    
    private double co2;

    /**
     * Constructs a Choice with the specified description and CO2 value.
     *
     * @param text the description of the choice
     * @param co2 the CO2 value associated with the choice
     */
    public Choice(String text, double co2) {
        this.choice = text;

        this.co2 = co2;
    }

    /**
     * Returns the description of this choice.
     *
     * @return the choice description
     */
    public String getChoice(){
        return choice;
    }

    /**
    * Returns the CO2 value associated with this choice.
    *
    * @return the CO2 value
    */
    public double getco2(){
        return co2;
    }
}