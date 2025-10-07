/**
 * Simple wrapper/manager for a list of {@link GameSession} objects as persisted in JSON.
 * <p>
 * This class provides access to the underlying session list via simple CRUD
 * operations and hides JSON file I/O behind a small API for loading/saving history.
 * </p>
 *
 * @author EcoLogic Studios
 */

package com.ecologicstudios.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Manages game session history with simple CRUD operations.
 */
public class GameHistory {
    private final String filePath;
    private final ObjectMapper mapper;
    
    public GameHistory(String filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
    }
    
    /**
     * Gets all game sessions from history.
     * @return List of all game sessions, empty list if none exist
     */
    public List<GameSession> getAllSessions() {
        return loadGameData().getGameSessions();
    }
    
    /**
     * Gets a specific game session by ID.
     * @param sessionId the session ID to find
     * @return the GameSession if found, null otherwise
     */
    public GameSession getSession(int sessionId) {
        return loadGameData().getGameFromId(sessionId);
    }
    
    /**
     * Adds a new game session to history.
     * @param session the session to add
     * @return the assigned session ID
     */
    public int addSession(GameSession session) {
        GameWrapper gameData = loadGameData();
        int nextId = getNextId(gameData.getGameSessions());
        session.setSessionId(nextId);
        gameData.getGameSessions().add(session);
        saveGameData(gameData);
        return nextId;
    }
    
    /**
     * Removes a game session by ID.
     * @param sessionId the ID of the session to remove
     * @return true if removed, false if not found
     */
    public boolean removeSession(int sessionId) {
        GameWrapper gameData = loadGameData();
        boolean removed = gameData.getGameSessions().removeIf(s -> s.getSessionId() == sessionId);
        if (removed) {
            saveGameData(gameData);
        }
        return removed;
    }
    
    /**
     * Gets the total number of sessions.
     * @return number of sessions in history
     */
    public int getSessionCount() {
        return getAllSessions().size();
    }
    
    // ------Private helper methods------

    /**
     * Loads game history from the configured JSON file.
     *
     * If the file does not exist or an I/O error occurs, an empty {@link GameWrapper}
     * is returned so callers always receive a non-null container.
     *
     * @return the deserialized {@link GameWrapper} read from disk, or an empty wrapper if missing/error
     */
    private GameWrapper loadGameData() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new GameWrapper();
            }
            return mapper.readValue(file, GameWrapper.class);
        } catch (IOException e) {
            System.err.println("Error loading game history: " + e.getMessage());
            return new GameWrapper();
        }
    }
    
    /**
     * Persists the given {@link GameWrapper} to the configured JSON file using
     * the mapper's pretty-printer.
     *
     * Errors are logged to stderr; callers should not expect an exception to be thrown.
     *
     * @param gameData the game data to save
     */
    private void saveGameData(GameWrapper gameData) {
        try {
            File file = new File(filePath);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, gameData);
        } catch (IOException e) {
            System.err.println("Error saving game history: " + e.getMessage());
        }
    }
    
    /**
     * Computes the next session id for the provided list of sessions.
     *
     * Finds the current maximum sessionId and returns max + 1. If the list is empty
     * the method returns 1.
     *
     * @param sessions the existing sessions to scan
     * @return the next available session id (max id + 1), at least 1
     */
    private int getNextId(List<GameSession> sessions) {
        return sessions.stream().mapToInt(GameSession::getSessionId).max().orElse(0) + 1;
    }
}