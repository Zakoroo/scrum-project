package com.ecologicstudios.utils;

public class Alternative{
    String choice;
    private int co2;

    public Alternative(String text, int co2) {
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