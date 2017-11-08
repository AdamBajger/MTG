package cz.mtg.exceptions;

import cz.mtg.cards.Card;

/**
 * This exception is thrown when someone is trying to destroy indestructible creature
 */
public class IndestructibleException extends Exception {
    public IndestructibleException(Card c) {
        super(c.getName() + " is indestructible.");
    }
}
