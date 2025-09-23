package com.ecologicstudios.utils;

public class test{

    public static void main(String[] args) {

        DeckFactory factory = new EasyCardFactory();
        
        Deck easyDeck = factory.createDeck();
        

        easyDeck.showDeck();
        //mediumDeck.showDeck();
        //hardDeck.showDeck();
    }
   
}