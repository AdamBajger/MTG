package cz.mtg.exceptions;

import cz.mtg.game.Deck;

public class EmptyDeckException extends Exception {
    private static final String MESSAGE = "Maindeck is empty in Deck: ";

    /**
     * Throws an exception with information about a given deck.
     * Specified message is appended.
     * @param deck given deck
     * @param message Specified message
     */
    public EmptyDeckException(Deck deck, String message) {
        super(EmptyDeckException.MESSAGE + deck + ". " + message);
    }

    /**
     * Throws an exception with information about a given deck.
     * @param deck given deck
     */
    public EmptyDeckException(Deck deck) {
        super(EmptyDeckException.MESSAGE + deck + ". " );
    }
}
