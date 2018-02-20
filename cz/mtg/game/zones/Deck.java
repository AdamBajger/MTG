package cz.mtg.game.zones;

import cz.mtg.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * This class represents deck
 * Basicly it is a Set of card classes and its numbers
 * It can contain any Card classes and when the game starts, cards are loaded from some Public Card library
 * (so both players have access to same cards) according to card class names listed in this Deck class
 * Somehow....
 * Not sure yet how to implement that...
 *
 */
public class Deck {
    //private final Player owner;
    // Library cards
    private final Set<Card> mainDeck;

    // sideboard cards
    private Set<Card> sideboard;

    public Deck(/*Player owner,*/ @NotNull Set<Card> mainDeck, @NotNull Set<Card> sideboard) {
        //this.owner = owner;
        this.mainDeck = mainDeck;
        this.sideboard = sideboard;
    }

    public Set<Card> getMainDeck() {
        return Collections.unmodifiableSet(mainDeck);
    }

    public boolean addCardToMainDeck(Card card) {
        return mainDeck.add(card);
    }

    public boolean addCardsToMainDeck(Collection<Card> cards) {
        return mainDeck.addAll(cards);
    }

    public boolean removeCardFromMainDeck() {
        // TODO: implement method --> just coolection adding/removing
        return false;
    }

    public boolean removeCardsFromMainDeck() {
        // TODO: implement method --> just coolection adding/removing
        return false;
    }

    public Set<Card> getSideboard() {
        return Collections.unmodifiableSet(sideboard);
    }

    public boolean addCardToSideboard(Card card) {
        return sideboard.add(card);
    }

    public boolean addCardsToSideboard(Collection<Card> cards) {
        return sideboard.addAll(cards);
    }

    public boolean removeCardFromSideboard() {
        // TODO: implement method --> just coolection adding/removing
        return false;
    }

    public boolean removeCardsFromSideboard() {
        // TODO: implement method --> just coolection adding/removing
        return false;
    }
}
