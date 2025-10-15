package com.ecologicstudios.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.Objects;

public final class Music {
    private static MediaPlayer player;
    private static double volume = 0.35; // default music volume (0.0–1.0)

    private Music() {
    }

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

    public static void pause() {
        if (player != null)
            player.pause();
    }

    public static void stop() {
        if (player != null)
            player.stop();
    }

    public static void dispose() {
        if (player != null) {
            player.dispose();
            player = null;
        }
    }

    public static void setVolume(double v) {
        volume = Math.max(0, Math.min(1, v));
        if (player != null)
            player.setVolume(volume);
    }

    public static void setMuted(boolean m) {
        if (player != null)
            player.setMute(m);
    }

    public static boolean isMuted() {
        return player != null && player.isMute();
    }

    public static boolean isPlaying() {
        return player != null && player.getStatus() == MediaPlayer.Status.PLAYING;
    }
}