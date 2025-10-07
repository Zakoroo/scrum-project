/**
 * Simple data holder representing a single game session entry.
 * <p>
 * This class captures metadata such as timestamp, difficulty, total rounds,
 * cumulative score and best round score for persistence and display.
 * </p>
 *
 * @author EcoLogic Studios
 */

package com.ecologicstudios.utils;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private int sessionId;
    private String timestamp;
    private String difficulty;
    private int totalRounds;
    private double totalScore;
    private double bestScore;
    private double worstScore;
    
    /**
     * Create an empty {@code GameSession} instance.
     * <p>
     * This no-arg constructor is provided for JSON deserialization libraries and
     * for simple instantiation where fields will be populated via setters.
     * </p>
     */
    public GameSession(){}

    /**
     * Constructs a {@code GameSession} with the provided values. The timestamp is
     * set to the current system time using the system default time zone.
     *
     * @param difficulty human-readable difficulty label (e.g. "Easy", "Hard")
     * @param totalRounds number of rounds played in this session
     * @param totalScore cumulative score across all rounds
     * @param bestScore highest single-round score in the session
     * @param worstScore lowest single-round score in the session
     */
    public GameSession(String difficulty, int totalRounds, double totalScore, double bestScore, double worstScore){
        this.sessionId = sessionId;
        this.timestamp = Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.difficulty = difficulty;
        this.totalRounds = totalRounds;
        this.totalScore = totalScore;
        this.bestScore = bestScore;
        this.worstScore = worstScore;
    }

    /**
     * Returns the numeric identifier assigned to this session.
     *
     * @return session id (may be 0 if not yet assigned)
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * Assigns a numeric identifier to this session. Typically set by the
     * persistence layer when the session is saved.
     *
     * @param sessionId identifier to assign
     */
    public void setSessionId(int sessionId){
        this.sessionId = sessionId;
    }
    
    /**
     * Returns the timestamp recorded for this session in yyyy-MM-dd HH:mm:ss format.
     *
     * @return timestamp string (may be {@code null} if not set)
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the difficulty label recorded for this session.
     *
     * @return difficulty string (may be {@code null})
     */
    public String getDifficulty() {
        return difficulty;
    }
    
    /**
     * Returns the number of rounds played in this session.
     *
     * @return total rounds
     */
    public int getTotalRounds() {
        return totalRounds;
    }

    /**
     * Returns the total cumulative score for this session.
     *
     * @return total score
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * Returns the highest single-round score from this session.
     *
     * @return best score
     */
    public double getBestScore() {
        return bestScore;
    }

    /**
     * Returns the worst (lowest) single-round score from this session.
     *
     * @return worst score
     */
    public double getWorstScore() {
        return worstScore;
    }
}