package cz.mtg.game.zones;

import cz.mtg.cards.Card;

import java.util.*;

/**
 * This class describes player's Library
 * Structure:
 *  Keep this in mind:
 *      First Card ---> top card of library
 *      Last Card ----> bottom card of a library
 *
 *      pop() is the same as removeFirst()
 *
 * -------------------------------------
 * TODO:
 *  implement methods:
 *      for searching for cards that match specific criteria (type, name, power, mana cost, etc.)
 *
 *
 */
public class Library {
    private final LinkedList<Card> cards = new LinkedList<>();

    /**
     * Method to shuffle Library
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Takes a card and puts it on top of this library
     * @param card card to be put on top
     */
    public void putCardOnTop(Card card) {
        cards.addFirst(card);
    }

    /**
     * Takes a card and puts it on the bottom of this library
     * @param card taken card
     */
    public void putCardOnBottom(Card card) {
        cards.addLast(card);
    }

    /**
     * Takes a top card from this library and returns it
     * the card is removed from the library in that process
     * If the library is already empty, exception is thrown
     * @return taken Card
     * @throws NoSuchElementException When the library is empty
     */
    public Card takeTopCard() throws NoSuchElementException {
        return cards.pop();
    }

    /**
     * Takes top N cards from library and removes them from the library.
     * Those cards are then returned. Order of those cards is the same as order in library
     * If there are less cards in the library than cards to be taken, it will just take the maximum amount
     * cards that are available.
     * @param n the number of cards to be taken from top
     * @return linked list of taken cards
     */
    public LinkedList<Card> takeTopNCards(int n) {
        LinkedList<Card> topCards = new LinkedList<>();
        //if(cards.size() == 0) throw new NoSuchElementException();
        for(int i = 0; i < n; i++) {
            try {
                topCards.addLast(cards.pop());
            } catch(NoSuchElementException e) {
                // nothing to do here
                // according to rules, if you are instructed to take some cards from library
                // and you have no library already, you just do nothing
            }
        }
        return topCards;
    }

    /**
     * Removes a given card from the library
     * @param card given card
     */
    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    /**
     * Removes all cards present in a given collection
     * @param cardsToRemove given collection
     * ------------------------------
     *  TODO:
     *      well it would be nice if we had some way to check if anything happened here
     *      like... which cards were removed and which were not found
     */
    public void removeCards(Collection<Card> cardsToRemove) {
        cards.removeAll(cardsToRemove);
    }



}
