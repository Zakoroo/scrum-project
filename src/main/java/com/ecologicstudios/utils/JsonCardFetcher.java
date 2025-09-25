package com.ecologicstudios.utils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


public class JsonCardFetcher implements CardFetcher{

        public Cards allCards;

        public JsonCardFetcher(){
                loadCards();
        }

        public String JsonToString(){
                StringBuilder stringbuilder = new StringBuilder();
                try (FileReader filereader = new FileReader("src/main/resources/cards.json")) {
                        int i;
                        while((i = filereader.read()) != -1) {
                        stringbuilder.append((char) i);
                        }
                        filereader.close();
                        return stringbuilder.toString();
                        } 
                catch (IOException e) {
                        e.printStackTrace();
                        return "";
                }
        }

        public void loadCards(){
                Gson gson = new Gson();
                allCards = gson.fromJson(JsonToString(), Cards.class);
        }
        
        
        public List<Card> getCards(String difficulty){

                List<Card> CardList = new ArrayList<>();
                for (Card c : allCards.getCardList()) {
                        if (c.difficulty.equalsIgnoreCase(difficulty)) {
                        CardList.add(c);
                        }
                }
                return CardList;
        }
}