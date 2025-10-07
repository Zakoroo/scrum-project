package com.ecologicstudios.client.models;

public class SettingsModel {
    private static SettingsModel instance;
    public static enum Theme {LIGHT, DARK};
    private Theme theme;

    public static SettingsModel getInstance() {
        if (instance == null) {
            instance = new SettingsModel();
        }
        return instance;
    }

    private SettingsModel() {
        this.theme = Theme.LIGHT;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
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
}