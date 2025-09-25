package com.ecologicstudios.client.models;

public class GameLoopModel {
    private int count = 0;
    private static GameLoopModel instance;

    public static GameLoopModel getInstance() {
        if (instance != null) {
            instance = new GameLoopModel();
        }
        return instance;
    }

    private GameLoopModel() {}

    public int increment() {
        count++;
        return count;
    }

    public boolean gameEnded() {
        return count == 10;
    }
}
