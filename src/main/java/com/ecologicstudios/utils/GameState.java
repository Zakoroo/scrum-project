package com.ecologicstudios.utils;

public class GameState {
    private int totalCO2;
    public  int getTotalCO2() {
        return totalCO2;
    }
    public void addCO2(int co2) {
        totalCO2 += co2;
    }
    
}
