// src/main/java/com/ecologicstudios/utils/Sound.java
package com.ecologicstudios.utils;

import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.Objects;

public final class Sound {
    private static final AudioClip CLICK = clip("/sounds/click.wav");
    private static final AudioClip GOOD_RESULT = clip("/sounds/good_result.wav");
    private static final AudioClip BAD_RESULT = clip("/sounds/bad_result.wav");

    // Master SFX volume (0.0–1.0)
    private static double volume = 1.0;

    private Sound() {
    }

    private static AudioClip clip(String path) {
        URL url = Objects.requireNonNull(Sound.class.getResource(path), "Missing resource: " + path);
        AudioClip c = new AudioClip(url.toExternalForm());
        c.setVolume(volume);
        return c;
    }

    public static void setVolume(double v) {
        volume = Math.max(0, Math.min(1, v));
        CLICK.setVolume(volume);
        GOOD_RESULT.setVolume(volume);
        BAD_RESULT.setVolume(volume);
    }

    public static void click() {
        CLICK.play();
    }

    public static void goodResult() {
        GOOD_RESULT.play();
    }

    public static void badResult() {
        BAD_RESULT.play();
    }
}
