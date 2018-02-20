package cz.mtg.exceptions;

import cz.mtg.cards.Card;
import cz.mtg.game.targets.Targetable;

/**
 * This exception is thrown whenever an invalid target would have been assigned
 */
public class InvalidTargetException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidTargetException(String message, Targetable target, Card source) {
        super(message);
    }
}
