package com.ecologicstudios.utils;

public class Choice{
    String text;
    public int co2;

    public Choice() {}
    public Choice(String text, int co2) {
        this.text = text;
        this.co2 = co2;
    }

    public String getChoice(){
        return text;
    }

    public int getco2(){
        return co2;
    }
}