package com.ecologicstudios.client.models;

import java.util.Objects;

import com.ecologicstudios.utils.Music;

public class AppContext {
    GameModel game;
    SettingsModel settings;
    Music music;

    public AppContext(GameModel game, SettingsModel settings, Music music) {
        this.game = Objects.requireNonNull(game, "game is null");
        this.settings = Objects.requireNonNull(settings, "settings is null");
        this.music = Objects.requireNonNull(music, "music is null");
    }

    public GameModel game() {
        return game;
    }

    public SettingsModel settings() {
        return settings;
    }

    public Music music() {
        return music;
    }
}
