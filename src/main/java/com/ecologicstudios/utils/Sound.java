package com.ecologicstudios.utils;

import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.Objects;

/**
 * Utility class for managing sound effects (SFX) in the application.
 * <p>
 * This class provides static methods to play predefined sound effects, such as
 * button clicks and result notifications. It also allows adjusting the master
 * volume for all sound effects.
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public final class Sound {
    private static final AudioClip CLICK = clip("/sounds/click.wav");
    private static final AudioClip GOOD_RESULT = clip("/sounds/good_result.wav");
    private static final AudioClip BAD_RESULT = clip("/sounds/bad_result.wav");

    // Master SFX volume (0.0–1.0)
    private static double volume = 1.0;

    private Sound() {
    }

    /**
     * Creates an AudioClip instance for the specified resource path.
     *
     * @param path the relative path to the audio resource (e.g., "/sounds/click.wav").
     * @return an AudioClip instance for the specified resource.
     * @throws NullPointerException if the resource is missing.
     */
    private static AudioClip clip(String path) {
        URL url = Objects.requireNonNull(Sound.class.getResource(path), "Missing resource: " + path);
        AudioClip c = new AudioClip(url.toExternalForm());
        c.setVolume(volume);
        return c;
    }

    /**
     * Sets the master volume for all sound effects.
     * <p>
     * The volume is clamped to the range [0.0, 1.0].
     *
     * @param v the desired volume level (0.0 for mute, 1.0 for maximum volume).
     */
    public static void setVolume(double v) {
        volume = Math.max(0, Math.min(1, v));
        CLICK.setVolume(volume);
        GOOD_RESULT.setVolume(volume);
        BAD_RESULT.setVolume(volume);
    }

    /**
     * Plays the click sound effect.
     */
    public static void click() {
        CLICK.play();
    }

    /**
     * Plays the good result sound effect.
     */
    public static void goodResult() {
        GOOD_RESULT.play();
    }

    /**
     * Plays the bad result sound effect.
     */
    public static void badResult() {
        BAD_RESULT.play();
    }
}
