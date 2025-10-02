package com.ecologicstudios.utils;

/**
 * Represents an alternative choice in an environmental scenario with its
 * associated CO2 emission value.
 * <p>
 * Each Alternative contains a textual description of a choice and the
 * corresponding environmental impact measured in CO2 emissions. This class is
 * used to present options to players and calculate the environmental
 * consequences of their decisions.
 * 
 * @author Ecologic Studios
 */
public class Alternative {
    /**
     * The textual description of this alternative choice.
     */
    String choice;

    /**
     * The CO2 value associated with this choice.
     */
    private double co2;

    /**
     * Constructs a new Alternative with the specified choice text and CO2 value.
     * 
     * @param text the descriptive text for this alternative choice
     * @param co2  the CO2 emission value associated with selecting this alternative
     */
    public Alternative(String text, int co2) {
        this.choice = text;

        this.co2 = co2;
    }

    /**
     * Returns the description of this choice.
     *
     * @return the choice description
     */
    public String getChoice() {
        return choice;
    }

    /**
     * Returns the CO2 value associated with this choice.
     *
     * @return the CO2 value representing the environmental impact of this choice
     */
    public double getco2() {
        return co2;
    }
}