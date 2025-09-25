package com.ecologicstudios.utils;
import java.util.List;

public class Cards{
    private List<Card> cards;

    public Cards(List<Card> cards){
        this.cards=cards;
    }

    public List<Card> getCardList(){
        return cards;
    }

    public void printScenario(int index){
        Card testcard = cards.get(index);
        System.out.printf(testcard.getScenario());
    }

    public void printDifficulty(int index){
        Card testcard = cards.get(index);
        System.out.printf(testcard.getDifficulty());
    }

}