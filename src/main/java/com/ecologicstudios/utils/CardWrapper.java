/**
 * Simple wrapper for a list of {@link Card} objects as deserialized from JSON.
 * <p>
 * This class provides access to the underlying card list and a couple of
 * convenience printing methods used in quick tests or debugging.
 * </p>
 *
 * @author EcoLogic Studios
 */

package com.ecologicstudios.utils;
import java.util.List;

public class CardWrapper{
    private List<Card> cards;

    /**
     * Create a new CardWrapper with the provided list of cards.
     *
     * @param cards the list of cards to wrap; may be {@code null} but callers should avoid that
     */

    public CardWrapper(List<Card> cards){
        this.cards=cards;
    }

    /**
     * Returns the wrapped list of cards.
     *
     * @return the list of {@link Card} objects (may be {@code null})
     */

    public List<Card> getCardList(){
        return cards;
    }

    /**
     * Print the scenario text for the card at the given index to standard output.
     * <p>
     * This method is intended for quick debugging or console output. It will
     * throw an {@link IndexOutOfBoundsException} if the index is invalid and a
     * {@link NullPointerException} if the internal list is {@code null}.
     * </p>
     *
     * @param index the index of the card whose scenario will be printed
     * @throws IndexOutOfBoundsException if {@code index} is out of range
     * @throws NullPointerException if the wrapped card list is {@code null}
     */

    public void printScenario(int index){
        Card testcard = cards.get(index);
        System.out.printf(testcard.getScenario());
    }

    /**
     * Print the difficulty value for the card at the given index to standard output.
     * <p>
     * Like {@link #printScenario(int)}, this is a small helper for debugging and may
     * throw runtime exceptions for invalid input or state.
     * </p>
     *
     * @param index the index of the card whose difficulty will be printed
     * @throws IndexOutOfBoundsException if {@code index} is out of range
     * @throws NullPointerException if the wrapped card list is {@code null}
     */

    public void printDifficulty(int index){
        Card testcard = cards.get(index);
        System.out.printf(testcard.getDifficulty());
    }

}