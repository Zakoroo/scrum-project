package com.ecologicstudios.utils;

public class Choice{
    String choice;
    private int co2;

    public Choice(String text, int co2) {
        this.choice = text;
        this.co2 = co2;
    }

    public String getChoice(){
        return choice;
    }

    public int getco2(){
        return co2;
    }
}