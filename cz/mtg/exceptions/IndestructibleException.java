package cz.mtg.exceptions;

import cz.mtg.game.Source;

/**
 * This exception is thrown when someone is trying to destroy indestructible creature
 */
public class IndestructibleException extends Exception {
    public IndestructibleException(Source s) {
        super(s.getName() + " is indestructible.");
    }
}
