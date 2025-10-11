package com.ecologicstudios.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Manages persistent game session history stored as JSON on disk.
 *
 * <p>
 * This class provides simple CRUD-style operations for reading and
 * writing game sessions. Internally it serializes a {@link GameWrapper}
 * using Jackson's {@link ObjectMapper} to the file provided by
 * {@code filePath}.
 *
 * <p>
 * All public methods are designed to avoid throwing checked
 * exceptions — I/O problems are logged to stderr and the class will
 * return empty containers or nulls to indicate missing data. Callers
 * should be prepared to handle empty lists or nulls when appropriate.
 *
 * <p>
 * Example usage:
 * 
 * <pre>
 * GameHistory history = new GameHistory("data/game_history.json");
 * int id = history.addSession(new GameSession(...));
 * </pre>
 *
 * @see GameWrapper
 * @see GameSession
 */
public class GameHistory {
    /** Path to the JSON file used to persist game history. */
    private final String filePath;

    /** Jackson mapper used for JSON (de)serialization. */
    private final ObjectMapper mapper;

    /**
     * Create a new GameHistory instance which will read and write
     * history to the supplied file path.
     *
     * @param filePath path to the JSON file used to persist history. If the
     *                 file does not exist it will be created by
     *                 {@link #saveGameData(GameWrapper)}
     */
    public GameHistory(String filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();

        // create history file if it does not exist
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                if (file.createNewFile()) {
                    throw new IOException(String.format("was not able to create file: %s", file.getParent()));
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Gets all game sessions from history.
     *
     * @return list of all {@link GameSession}s. Never null — returns an empty
     *         list when no sessions are present or on read error.
     */
    public List<GameSession> getAllSessions() {
        return loadGameData().getGameSessions();
    }

    /**
     * Gets a specific game session by ID.
     *
     * @param sessionId the session ID to find
     * @return the {@link GameSession} matching {@code sessionId} or {@code null}
     *         if not found
     */
    public GameSession getSession(int sessionId) {
        return loadGameData().getGameFromId(sessionId);
    }

    /**
     * Adds a new game session to history.
     *
     * The method assigns a unique session id to the provided session and
     * persists the updated history to disk.
     *
     * @param session the session to add; must not be null
     * @return the assigned session id (>= 1)
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
     *
     * @param sessionId the ID of the session to remove
     * @return {@code true} if a session was removed and data persisted,
     *         otherwise {@code false}
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
     * Clears all game session history.
     * 
     * Removes all sessions from memory and persists an empty history to disk.
     * This operation cannot be undone.
     */
    public void clearHistory() {
        GameWrapper emptyGameData = new GameWrapper();
        saveGameData(emptyGameData);
    }

    /**
     * Gets the total number of sessions.
     *
     * @return number of sessions in history; 0 if none or on read error
     */
    public int getSessionCount() {
        return getAllSessions().size();
    }

    // ------Private helper methods------

    /**
     * Loads game history from the configured JSON file.
     *
     * If the file does not exist or an I/O error occurs, an empty
     * {@link GameWrapper}
     * is returned so callers always receive a non-null container.
     *
     * @return the deserialized {@link GameWrapper} read from disk, or an empty
     *         wrapper if missing/error
     */
    private GameWrapper loadGameData() {
        try {
            File file = new File(filePath);
            // if file is empty
            if (file.length() == 0) {
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
     * Errors are logged to stderr; callers should not expect an exception to be
     * thrown.
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