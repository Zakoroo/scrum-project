package com.ecologicstudios.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.Objects;

/**
 * Utility class for managing background music playback.
 * <p>
 * This class provides static methods to control background music, including
 * playing, pausing, stopping, and adjusting volume. It uses JavaFX's MediaPlayer
 * to handle audio playback.
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public final class Music {
    private static MediaPlayer player;
    private static double volume = 0.35; // default music volume (0.0–1.0)

    private Music() {
    }

    /**
     * Plays the background music in a loop.
     * <p>
     * If the MediaPlayer is not initialized, it loads the background music
     * resource and starts playback. Otherwise, it resumes playback.
     *
     * @throws NullPointerException if the background music resource is missing.
     */
    public static void playBackground() {
        if (player == null) {
            URL url = Objects.requireNonNull(
                    Music.class.getResource("/sounds/background.mp3"),
                    "Missing resource: /sounds/background.mp3");
            player = new MediaPlayer(new Media(url.toExternalForm()));
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.setVolume(volume);
        }
        player.play();
    }

    /**
     * Pauses the background music if it is currently playing.
     */
    public static void pause() {
        if (player != null)
            player.pause();
    }

    /**
     * Stops the background music playback.
     * <p>
     * This method stops the MediaPlayer and resets its playback position.
     */
    public static void stop() {
        if (player != null)
            player.stop();
    }

    /**
     * Releases resources used by the MediaPlayer.
     * <p>
     * This method disposes of the MediaPlayer instance and sets it to null.
     */
    public static void dispose() {
        if (player != null) {
            player.dispose();
            player = null;
        }
    }

    /**
     * Sets the volume for the background music.
     * <p>
     * The volume is clamped to the range [0.0, 1.0].
     *
     * @param v the desired volume level (0.0 for mute, 1.0 for maximum volume).
     */
    public static void setVolume(double v) {
        volume = Math.max(0, Math.min(1, v));
        if (player != null)
            player.setVolume(volume);
    }

    /**
     * Mutes or unmutes the background music.
     *
     * @param m {@code true} to mute the music, {@code false} to unmute.
     */
    public static void setMuted(boolean m) {
        if (player != null)
            player.setMute(m);
    }

    /**
     * Checks if the background music is muted.
     *
     * @return {@code true} if the music is muted, {@code false} otherwise.
     */
    public static boolean isMuted() {
        return player != null && player.isMute();
    }

    /**
     * Checks if the background music is currently playing.
     *
     * @return {@code true} if the music is playing, {@code false} otherwise.
     */
    public static boolean isPlaying() {
        return player != null && player.getStatus() == MediaPlayer.Status.PLAYING;
    }
}