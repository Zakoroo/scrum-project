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

        public JsonCardFetcher(String path){
                loadCards(path);
        }

        public String JsonToString(String path){
                StringBuilder stringbuilder = new StringBuilder();
                try (FileReader filereader = new FileReader(path)) {
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

        public void loadCards(String path){
                Gson gson = new Gson();
                allCards = gson.fromJson(JsonToString(path), Cards.class);
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

        public List<Card> getAllCards(){
           return allCards.getCardList();
        }
}