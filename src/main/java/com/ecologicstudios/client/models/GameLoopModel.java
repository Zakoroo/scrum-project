package com.ecologicstudios.client.models;

public class GameLoopModel {
    private int count = 0;

    public int increment() {
        count++;
        return count;
    }

    public boolean gameEnded() {
        return count == 10;
    }
}
