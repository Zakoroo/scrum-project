package com.ecologicstudios.utils;
import java.util.ArrayList;
import java.util.List;

public class GameData{
    private List<GameSession> gameSessions = new ArrayList<GameSession>();

    public GameData(){}

    public List<GameSession> getGameSessions(){
        return gameSessions;
    }

    
    public void setGameSessions(List<GameSession> gameSessions){
        this.gameSessions = gameSessions;
    }

    public void removeSession(int id){
        boolean removed = gameSessions.removeIf(s -> s.getSessionId() == id);
        if (!removed) {
            System.out.println("No session found with ID: " + id);
        }
    }

    public GameSession getGameFromId(int i){
        for (GameSession session : gameSessions) {
            if (session.getSessionId() == i) {
                return session;
            }
        }
        return null;
        
    }
    
}