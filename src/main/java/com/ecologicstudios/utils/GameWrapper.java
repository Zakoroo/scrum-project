package com.ecologicstudios.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Wrapper container for persisting and transporting a collection of {@link GameSession} objects.
 * Primarily used for JSON serialization/deserialization of the game history.
 * </p>
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class GameWrapper{
    private List<GameSession> gameSessions = new ArrayList<GameSession>();

    /** Default constructor required for JSON libraries. */
    public GameWrapper(){}

    /**
     * Returns the list of all stored game sessions.
     *
     * @return mutable list of sessions (never null)
     */
    public List<GameSession> getGameSessions(){
        return gameSessions;
    }

    /**
     * Replaces the internal list of game sessions.
     *
     * @param gameSessions new list of sessions to set
     */
    public void setGameSessions(List<GameSession> gameSessions){
        this.gameSessions = gameSessions;
    }

    /**
     * Removes a session matching the given ID.
     *
     * @param id the session identifier
     */
    public void removeSession(int id){
        boolean removed = gameSessions.removeIf(s -> s.getSessionId() == id);
        if (!removed) {
            System.out.println("No session found with ID: " + id);
        }
    }

    /**
     * Retrieves a session by its ID.
     *
     * @param i the session identifier
     * @return the matching {@link GameSession} if present, otherwise null
     */
    public GameSession getGameFromId(int i){
        for (GameSession session : gameSessions) {
            if (session.getSessionId() == i) {
                return session;
            }
        }
        return null;
        
    }
    
}