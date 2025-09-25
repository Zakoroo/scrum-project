package com.ecologicstudios.utils;
import java.util.List;

public interface CardFetcher {
    public void loadCards(String path);
    public List<Card> getCards(String difficulty);
    public List<Card> getAllCards();
}

