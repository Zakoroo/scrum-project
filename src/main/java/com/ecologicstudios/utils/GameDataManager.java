package com.ecologicstudios.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class GameDataManager {
    private String path; //"src/main/resources/game_sessions.json"

    public GameDataManager(String path) {
        this.path = path;
    }
    
    public void saveGameSession(GameSession newSession) {
        try {
            File file = new File(path);
            ObjectMapper mapper = new ObjectMapper();
            GameData gameData; 
            
            if (file.exists()) {
                gameData = mapper.readValue(file, GameData.class);
            } else {
                gameData = new GameData();
            }

            int nextId = calculateNextId(gameData.getGameSessions());
            newSession.setSessionId(nextId);
            
            gameData.getGameSessions().add(newSession);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, gameData);
            
            System.out.println("session saved with ID: " + newSession.getSessionId());
            
        } catch (Exception e) {
            System.err.println("Error saving game session: " + e.getMessage());
        }
    }

    public int calculateNextId(List<GameSession> sessions){
        if(sessions == null || sessions.isEmpty()){
            return 1;
        }
        int maxId = sessions.stream().mapToInt(GameSession::getSessionId).max().orElse(0);
        return maxId + 1;
        
    }

    public GameData getHistory(){
        try {
            File file = new File(path);
            ObjectMapper mapper = new ObjectMapper();
            
            if (!file.exists()) {
                return new GameData();
            }
            GameData gameData = mapper.readValue(file, GameData.class);
            return gameData;
        }
        catch (Exception e) {
            System.err.println("Error couldn not find any game sessions: " + e.getMessage());
            return null;
        }
    }
    
    public void deleteGameSession(int id) {
        try {
            File file = new File(path);
            ObjectMapper mapper = new ObjectMapper();
            GameData gameData;
            
            if (!file.exists()) {
                System.out.println("No history to delete from");
            }
            // Fetches List<GameSession> from JSON file
            gameData = mapper.readValue(file, GameData.class);
            
            // Removes the session with the given id and writes the updated list back to the file
            gameData.removeSession(id);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, gameData);
            
        } catch (Exception e) {
            System.err.println("Error deleting game session: " + e.getMessage());
        }
    }
}