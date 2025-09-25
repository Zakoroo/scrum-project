package com.ecologicstudios.utils;
import com.ecologicstudios.utils.DeckFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EasyCardFactory implements DeckFactory{


    // test data
        private static List<Card> getAllCards() {
            List<Card> all = new ArrayList<>();

            all.add(new Card(
                    1,
                    "You're at home feeling thirsty. Where do you get water from?",
                    "Easy",
                    Arrays.asList(
                            new Choice("Bottled Water", 20),
                            new Choice("Tap Water", 10)
                    )
            ));

            all.add(new Card(
                    2,
                    "You need a coffee before work. Which cup do you use?",
                    "Easy",
                    Arrays.asList(
                            new Choice("Disposable cup", 30),
                            new Choice("Thermos", 15)
                    )
            ));

            all.add(new Card(
                    3,
                    "You’re late for class. How do you travel?",
                    "Normal",
                    Arrays.asList(
                            new Choice("Drive", 40),
                            new Choice("Bus", 15),
                            new Choice("Bike", 5)
                    )
            ));

            all.add(new Card(
                    4,
                    "You’re planning a vacation abroad. Which transport?",
                    "Hard",
                    Arrays.asList(
                            new Choice("Plane", 200),
                            new Choice("Train", 50),
                            new Choice("Bus", 30)
                    )
            ));

            return all;
        }

    @Override
    public Deck createDeck() {
        List<Card> filtered = new ArrayList<>();
        for (Card c : getAllCards()) {
            if (c.difficulty.equalsIgnoreCase("Hard")) {
                filtered.add(c);
            }
        }
        return new Deck(filtered);
    }

}