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

    public GameSession(){}

    public GameSession(String difficulty, int totalRounds, double totalScore, double bestScore) {
        this.sessionId = sessionId;
        this.timestamp = Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.difficulty = difficulty;
        this.totalRounds = totalRounds;
        this.totalScore = totalScore;
        this.bestScore = bestScore;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId){
        this.sessionId = sessionId;
    }
    
    public String getTimestamp() {
        return timestamp;
    }

    public String getDifficulty() {
        return difficulty;
    }
    
    public int getTotalRounds() {
        return totalRounds;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public double getBestScore() {
        return bestScore;
    }

    public List<String> getResult(){
        List<String> resultList = new ArrayList<>();
        resultList.add("Session ID: " + sessionId);
        resultList.add("Timestamp: " + timestamp);
        resultList.add("Difficulty: " + difficulty);
        resultList.add("Total Rounds: " + totalRounds);
        resultList.add("Total Score: " + totalScore);
        resultList.add("Best Score: " + bestScore);
        return resultList;
    }
}