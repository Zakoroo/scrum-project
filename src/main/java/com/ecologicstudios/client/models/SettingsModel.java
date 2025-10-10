package com.ecologicstudios.client.models;

public class SettingsModel {
    private static SettingsModel instance;
    public static enum Theme {LIGHT, DARK};
    public static enum Sound {ON, OFF};
    private Theme theme;
    private Sound sound;

    public static SettingsModel getInstance() {
        if (instance == null) {
            instance = new SettingsModel();
        }
        return instance;
    }

    private SettingsModel() {
        this.theme = Theme.LIGHT;
        this.sound = Sound.ON;
    }

    public void toggleSound() {
       this.sound = this.sound == Sound.ON  ? Sound.OFF : Sound.ON;
    }

    public Theme toggleTheme() {
         return this.theme = (this.theme == Theme.LIGHT) ? Theme.DARK : Theme.LIGHT;
    }

    public String getStylesheet() {
        return (this.theme == Theme.DARK) ? "/style/dark_theme.css" : "/style/light_theme.css";
    }

    public Theme getTheme() {
        return this.theme;
    } 

   public Sound getSound() {
        return this.sound;
   }

}