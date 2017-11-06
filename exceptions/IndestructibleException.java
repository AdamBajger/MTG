package cz.mtg.exceptions;

import cz.mtg.cards.CardInterface;

/**
 * This exception is thrown when someone is trying to destroy indestructible creature
 */
public class IndestructibleException extends Exception {
    public IndestructibleException(CardInterface c) {
        super(c.getName() + " is indestructible.");
    }
}
