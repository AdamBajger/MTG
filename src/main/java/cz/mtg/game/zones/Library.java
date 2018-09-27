package cz.mtg.game.zones;

import cz.mtg.cards.Card;
import cz.mtg.exceptions.EmptyDeckException;
import org.jetbrains.annotations.NotNull;

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
public class Library implements Iterable<Card> {
    private final LinkedList<Card> cards = new LinkedList<>();

    /**
     * Tries to initialize the library with a given deck and shuffles it.
     * @param deck given Deck
     * @throws EmptyDeckException when an empty deck is provided
     */
    public void initializeLibrary(Deck deck) throws EmptyDeckException {
        Collection<Card> mainDeck = deck.getMainDeck();
        if(mainDeck == null || mainDeck.size() == 0) throw new EmptyDeckException(deck);
        //this will properly add all cards to a player's library
        for (Card card : mainDeck) {
            putCardOnTop(card);
        }
        shuffle();
    }

    /**
     * Method to shuffle Library
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Searches for a card by its name
     * @param name name of the card
     * @return the card
     */
    public Card searchForACard(String name) {
        for(Card c : cards) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Searches for the same card as the card inputted
     * @param card compared card
     * @return the card we are searching for
     */
    public Card searchForACard(Card card) {
        return searchForACard(card.getName());
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
    public Card lookAtTopCard() throws NoSuchElementException {
        return cards.get(0);
    }

    /**
     * Takes top N cards from library and removes them from the library.
     * Those cards are then returned. Order of those cards is the same as order in library
     * If there are less cards in the library than cards to be taken, it will just take the maximum amount
     * cards that are available.
     * @param n the number of cards to be taken from top
     * @return linked list of taken cards
     */
    public LinkedList<Card> lookAtTopNCards(int n) {
        LinkedList<Card> topCards = new LinkedList<>();
        if(cards.size() < n) {
            n = cards.size();
        }

        for(int i = 0; i < n; i++) {
            topCards.addLast(cards.get(i));
        }
        return topCards;
    }

    /**
     * returns the bottom card of library
     * @return the bottom card
     */
    public Card lookAtBottomCard() {
        return cards.getLast();
    }

    /**
     * returns the bottom N cards of the player's library
     * @param n the number of cards returned
     * @return the cards
     */
    public LinkedList<Card> lookAtBottomNCards(int n) {
        LinkedList<Card> result = new LinkedList<>();
        int numberOfCards = cards.size();
        for(int i = numberOfCards-1; i > (numberOfCards - (n + 1)); i--) {
            result.addFirst(cards.get(i));
        }
        return result;
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


    public int getNumberOfCards() {
        return cards.size();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
