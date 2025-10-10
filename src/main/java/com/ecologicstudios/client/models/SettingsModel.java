package com.ecologicstudios.client.models;

/**
 * Singleton model class for managing application settings.
 * <p>
 * This class provides methods to manage and toggle application settings such as
 * theme and sound. It ensures that only one instance of the settings model exists
 * throughout the application lifecycle.
 */
public class SettingsModel {

    /**
     * The single instance of the SettingsModel.
     */
    private static SettingsModel instance;

    /**
     * Enum representing the available themes for the application.
     */
    public static enum Theme {LIGHT, DARK};

    /**
     * Enum representing the sound settings for the application.
     */
    public static enum Sound {ON, OFF};

    /**
     * The current theme of the application.
     */
    private Theme theme;

    /**
     * The current sound setting of the application.
     */
    private Sound sound;

    /**
     * Retrieves the singleton instance of the SettingsModel.
     * <p>
     * If the instance does not already exist, it is created.
     * 
     * @return the singleton instance of the SettingsModel
     */
    public static SettingsModel getInstance() {
        if (instance == null) {
            instance = new SettingsModel();
        }
        return instance;
    }

    /**
     * Private constructor to prevent direct instantiation.
     * <p>
     * Initializes the settings model with default values for theme and sound.
     */
    private SettingsModel() {
        this.theme = Theme.LIGHT;
        this.sound = Sound.ON;
    }

    /**
     * Toggles the sound setting between ON and OFF.
     */
    public void toggleSound() {
       this.sound = this.sound == Sound.ON  ? Sound.OFF : Sound.ON;
    }

    /**
     * Toggles the theme between LIGHT and DARK.
     * 
     * @return the updated theme after toggling
     */
    public Theme toggleTheme() {
         return this.theme = (this.theme == Theme.LIGHT) ? Theme.DARK : Theme.LIGHT;
    }

    /**
     * Retrieves the stylesheet path corresponding to the current theme.
     * 
     * @return the path to the stylesheet for the current theme
     */
    public String getStylesheet() {
        return (this.theme == Theme.DARK) ? "/style/dark_theme.css" : "/style/light_theme.css";
    }

    /**
     * Retrieves the current theme of the application.
     * 
     * @return the current theme
     */
    public Theme getTheme() {
        return this.theme;
    } 

    /**
     * Retrieves the current sound setting of the application.
     * 
     * @return the current sound setting
     */
   public Sound getSound() {
        return this.sound;
   }
}